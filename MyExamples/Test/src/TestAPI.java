import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class TestAPI {

	public static void main(String[] args) {
		
		try {
			URL url = new URL("http://api.browserstack.com/3/browsers");
			URLConnection uc = url.openConnection();
			
			String username = "rajni.hemant@gmail.com";
            String password = "hkgbs123";
			
			String userpass = username + ":" + password;
	        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
	        uc.setRequestProperty ("Authorization", basicAuth);
	        
			HttpURLConnection conn = (HttpURLConnection) uc;
			
			uc.setRequestProperty ("Authorization", basicAuth);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
	       
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
