import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class Main {
	public static void main(String[] args) throws IOException {
		String clientId = "d9f9a50903ea4e1092469b4804237a5a";
		String clientSecret = "4fe82076c53e48ceab6e2fce1dfd72d2";
		String grantType = "authorization_code";
		String redirectUri = "http://localhost:8080/instagram-assistant/instagram/serverSideAssessToken";
		String code = "ee7363e0bd954c7c984d0c5722c61828";
		String httpsURL = "https://api.instagram.com/oauth/access_token";
//		String query = "grant_type=" + URLEncoder.encode(grantType,"UTF-8") + "&client_id=" + URLEncoder.encode(clientId,"UTF-8") + "&client_secret="
//				+ URLEncoder.encode(clientSecret,"UTF-8") + "&redirect_uri=" + URLEncoder.encode(redirectUri,"UTF-8") + "&code=" + URLEncoder.encode(code,"UTF-8");
		
		String query = "client_id=d9f9a50903ea4e1092469b4804237a5a&client_secret=4fe82076c53e48ceab6e2fce1dfd72d2&grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Finstagram-assistant%2Finstagram%2FserverSideAssessToken&code=2239a208b1e74f0aa3c014038c2e0edc";

		//
//		
//		 curl -F 'client_id=d9f9a50903ea4e1092469b4804237a5a' \
//		    -F 'client_secret=4fe82076c53e48ceab6e2fce1dfd72d2' \
//		    -F 'grant_type=authorization_code' \
//		    -F 'redirect_uri=http://localhost:8080/instagram-assistant/instagram/serverSideAssessToken' \
//		    -F 'code=ee7363e0bd954c7c984d0c5722c61828' \
//		    https://api.instagram.com/oauth/access_token
		    	
		    	
		URL myurl = new URL(httpsURL);
		HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
		con.setRequestMethod("POST");

		con.setRequestProperty("Content-length", String.valueOf(query.length())); 
		con.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
//		con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)"); 
		con.setDoOutput(true); 
		con.setDoInput(true); 

		DataOutputStream output = new DataOutputStream(con.getOutputStream());  


		output.writeBytes(query);

		output.close();
		
		DataInputStream input = new DataInputStream( con.getInputStream() ); 



		for( int c = input.read(); c != -1; c = input.read() ) 
		System.out.print( (char)c ); 
		input.close(); 

		System.out.println("Resp Code:"+con .getResponseCode()); 
		System.out.println("Resp Message:"+ con .getResponseMessage()); 
	}
} 
