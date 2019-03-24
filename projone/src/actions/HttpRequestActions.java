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
	public static String accessToken = "dGiyJl8RzZIl9B8CdGuPYVW52AVx";
	
	
	// https://community.liferay.com/forums/-/message_boards/message/34225286
	// https://www.programcreek.com/java-api-examples/?class=org.json.JSONObject&method=getJSONArray
	// https://stackoverflow.com/questions/31019391/how-to-fetch-all-the-nodes-and-child-nodes-of-json-object-in-java -- see last solution
	public static void Connect()    throws IOException, JSONException 
	{
		boolean foundCurrency = true; 
		JSONObject currencyNode = new JSONObject();
		
		System.out.println("start ---");
		
		String url = "https://tngo-qa-mobproc.cloudhub.io/mobproc/v1.qa/regions?type=ALL";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		System.out.println("HttpURLConnection returned = " + con);
	
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
		
		ShowText("Response = " + response.toString());

        JSONArray jasonArray = new JSONArray(response.toString().split("\"" + "regions" + "\"" + " : ")[1]); // setup array of nodes in response        
        
        
        for(int index = 0; index < jasonArray.length(); index++) // go through each node
        {
        	ShowText("------------------------------------------------------------------");
        	JSONObject  jObject = jasonArray.getJSONObject(index);
        	JSONObject two = jObject.getJSONObject("_meta");
        	
        	currencyNode = GetNode(currencyNode, "currency");
        		
        	// https://www.fusioncharts.com/charts/column-bar-charts/simple-column-chart
        	
        	/*
        	try // currency node may not be there - try/catch.
        	{
        		currencyNode = jObject.getJSONObject("currency");
        	}
        	catch (Exception e)
        	{
            	// ShowText(e.getMessage());    
            	foundCurrency = false;
        	}
        	finally
        	{
        		if(foundCurrency)
        		{
        			ShowText(currencyNode.getString("name"));
        		}
        		else
        		{
        			ShowText("---- currency node null");
            		foundCurrency = true;
        		}
        	}
        	*/
        	ShowText(jObject.getString("abbreviation"));
        	ShowText(two.getString("href"));
        	ShowText(jObject.getString("id"));
        	ShowText(jObject.getString("name"));
        	
        	if(index == 25) {
        		break;
        	}
        }
        
        ShowText("Done");
	}
	
	// ////////////////////////////////////////////////////////////
	// 						helpers
	// //////////////////////////////////////////////////////////// 
	
	public static JSONObject GetNode(JSONObject jsonObject ,String nodeName){
		
		JSONObject handle = new JSONObject();
		boolean foundNode = true;
		
		try{
			handle = jsonObject.getJSONObject(nodeName);
		}
		catch (Exception e){
			foundNode = false;
		}
		
		if(foundNode){
			return handle;
		}
		else {
			return null;
		}
	}
	
	//https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/ --- KEEP THIS URL FOR REFERENCE ON POSTS !!!!!!!!!!!!!!!!!!!!
	public static void SetToken() throws IOException
	{
		String url = "https://oauthqa.tangoe.com/as/token.oauth2";
		     	    //https://oauthqa.tangoe.com/as/token.oauth2?client_id=392d0283466b4e00be8e8b8062db5f9f&client_secret=3c684c3220e740ef92E7177A32AE978D&grant_type=password&scope=MOBPROC&username=command://ana.pace.xx1#xx1&password=tngo777
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language","en-US,en;q=0.5");	
		//con.setRequestProperty("Content-Type","application/json"); // this was not needed - was needed in Normandy - maybe because 
		// new below - this is what is in Ready API Http log
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

		Assert.assertEquals(con.getResponseCode(), 200);
		
		accessToken = response.toString().split(":")[4].replace("\"", "").replace("}",""); // NOTE: this could be done using json array.
		
		System.out.println("Access token = " + accessToken);
	}
	
	public static void SetToken(String fullResponse)
	{
		fullResponse = "{\"token_type\":\"Bearer\",\"expires_in\":7199,\"refresh_token\":\"wROw9bXhQ8CMrq7PX07mzhwTKpc9fZZNgHNrD6P5n0\",\"access_token\":\"TWIYdc1LGgYSVcGtSCJeJ9ZEW0KC\"}";
		ShowText(fullResponse.split(":")[4].replace("\"", "").replace("}",""));
		accessToken = fullResponse.split(":")[4].replace("\"", "").replace("}","");
	}
	
	
	
	
}
