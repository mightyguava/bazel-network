import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class NetworkTest {
  @Test
  public void testNetwork() throws Exception {
    HttpURLConnection connection = null;
    StringBuffer response;

    try {
      //Create connection
      URL url = new URL("https://www.google.com");
      connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");

      //Get Response
      InputStream is = connection.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
      response = new StringBuffer();
      String line;
      while ((line = rd.readLine()) != null) {
        response.append(line);
        response.append('\n');
      }
      rd.close();
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }

    assertThat(response.toString(), containsString("<!doctype html>"));
  }
}
