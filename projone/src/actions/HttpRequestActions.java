package actions;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray; // added org.json.jar for this
import org.json.JSONException; // added org.json.jarfor this
import org.json.JSONObject;
import org.testng.Assert;

import com.google.gson.JsonObject;

import tests.BaseSelenium;

public class HttpRequestActions extends BaseSelenium
{
	public static String accessToken = "WAIkClj3VW5UeFAZbx5680oTuprd";
	
	
	// https://community.liferay.com/forums/-/message_boards/message/34225286
	public static void Connect()    throws IOException, JSONException 
	{
		System.out.println("start");
		String url = "https://tngo-qa-mobproc.cloudhub.io/mobproc/v1.qa/regions?type=ALL";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		System.out.println(con);
	
		// optional default is GET
		con.setRequestMethod("GET");

		// set header info 
		con.setRequestProperty("Authorization", "Bearer " + accessToken);
		con.setRequestProperty("X-TNGO-TENANT", "XX1");
		con.setRequestProperty("client_id", "392d0283466b4e00be8e8b8062db5f9f");
		con.setRequestProperty("client_secret", "3c684c3220e740ef92E7177A32AE978D");
		
		 // get response code and verify
		int responseCode = con.getResponseCode();
		System.out.println("response code = " + responseCode);
		Assert.assertEquals(200, responseCode);
		
		// get stream
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) 
		{
			response.append(inputLine);
		}
		in.close();
		
		ShowText("Response Code = " + response.toString());

        JSONArray jasonArray = new JSONArray(response.toString().split("\"" + "regions" + "\"" + " : ")[1]);        
        
        for(int index = 0; index < jasonArray.length(); index++)
        {
        	JSONObject  jo = jasonArray.getJSONObject(index);
        	//JSONObject activeNodeJsonObject = jo.getJSONObject("currency");
        	ShowText(jo.getString("id"));
        	ShowText(jo.getString("name"));
        	
        	if(index == 5) {
        		break;
        	}
        		
        }
        
        ShowText("Done");
	}
	
	// ////////////////////////////////////////////////////////////
	// 						helpers
	// //////////////////////////////////////////////////////////// 
	
	//https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/ --- KEEP THIS URL FOR REFERENCE ON POSTS !!!!!!!!!!!!!!!!!!!!
	public static void SetToken() throws IOException
	{
		String url = "https://oauthqa.tangoe.com/as/token.oauth2";
		     	    //https://oauthqa.tangoe.com/as/token.oauth2?client_id=392d0283466b4e00be8e8b8062db5f9f&client_secret=3c684c3220e740ef92E7177A32AE978D&grant_type=password&scope=MOBPROC&username=command://ana.pace.xx1#xx1&password=tngo777
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language","en-US,en;q=0.5");	
		//con.setRequestProperty("Content-Type","application/json"); // this was needed - was used in Normandy
		// new below
		//con.setRequestProperty("Accept-Encoding","gzip,deflate");
		//con.setRequestProperty("Content-Length","0");
		//con.setRequestProperty("Host","oauthqa.tangoe.com");
		//con.setRequestProperty("Connection","Keep-Alive");
		//con.setRequestProperty("User-Agent","Apache-HttpClient/4.5.2 (Java/1.8.0_181)");		
		
		String urlParameters = "client_id=392d0283466b4e00be8e8b8062db5f9f&client_secret=3c684c3220e740ef92E7177A32AE978D&grant_type=password&scope=MOBPROC&username=command://ana.pace.xx1#xx1&password=tngo777";
		

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
	
		System.out.println(con.getResponseCode());
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println("Response ' " + response.toString());

		Assert.assertEquals(con.getResponseCode(), "200");
		
		accessToken = response.toString().split(":")[4].replace("\"", "").replace("}",""); // NOTE: this could be done using json array.
		
		System.out.println(accessToken);
	}
	
	public static void SetToken(String fullResponse)
	{
		fullResponse = "{\"token_type\":\"Bearer\",\"expires_in\":7199,\"refresh_token\":\"wROw9bXhQ8CMrq7PX07mzhwTKpc9fZZNgHNrD6P5n0\",\"access_token\":\"TWIYdc1LGgYSVcGtSCJeJ9ZEW0KC\"}";
		ShowText(fullResponse.split(":")[4].replace("\"", "").replace("}",""));
		accessToken = fullResponse.split(":")[4].replace("\"", "").replace("}","");
	}
	
	
	
	
}
