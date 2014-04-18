import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class TestPostAPI {

	public static void main(String[] args) {
		
		try {
			URL url = new URL("http://api.browserstack.com/3/worker");
			URLConnection uc = url.openConnection();
			uc.setDoOutput(true);
			
			String username = "rajni.hemant@gmail.com";
            String password = "hkgbs123";
			
			String userpass = username + ":" + password;
	        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
	        uc.setRequestProperty ("Authorization", basicAuth);
	        
			HttpURLConnection conn = (HttpURLConnection) uc;
			
			uc.setRequestProperty ("Authorization", basicAuth);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String input = "{\"os\":\"Windows\", \"os_version\":\"XP\", \"browser\":\"ie\", \"browser_version\":\"8.0\", \"url\":\"https://www.google.co.in\"}";
			//String input = "{os:\'Windows\', os_version:\'XP\', browser:\'ie\', browser_version:\'8.0\', url:\'https://github.com\'}";
		    System.out.println(input);
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
	       
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
	 
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
	 
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
	 
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
