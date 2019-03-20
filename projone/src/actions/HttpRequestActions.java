package actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray; // added org.json.jar for this
import org.json.JSONException; // added org.json.jarfor this
import org.json.JSONObject;
import org.testng.Assert;

import com.google.gson.JsonObject;

import tests.BaseSelenium;

public class HttpRequestActions extends BaseSelenium
{

	public static void Connect()    throws IOException, JSONException 
	{
		System.out.println("start");
		String url = "https://tngo-qa-mobproc.cloudhub.io/mobproc/v1.qa/regions?type=ALL";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		System.out.println(con);
	
		// optional default is GET
		con.setRequestMethod("GET");

		con.setRequestProperty("Authorization", "Bearer " + "fDjHK1dPwGP9T79EqWHrVSR7I9FQ");

		con.setRequestProperty("X-TNGO-TENANT", "XX1");
		con.setRequestProperty("client_id", "392d0283466b4e00be8e8b8062db5f9f");
		con.setRequestProperty("client_secret", "3c684c3220e740ef92E7177A32AE978D");
		int responseCode = con.getResponseCode();

		System.out.println("response code = " + responseCode);
		Assert.assertEquals(200, responseCode);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) 
		{
			response.append(inputLine);
		}
		in.close();
		
		ShowText(response.toString());

        JSONArray jasonArray = new JSONArray(response.toString().split("\"" + "regions" + "\"" + " : ")[1]);        
        
        for(int index = 0; index < jasonArray.length(); index++)
        {
        	JSONObject  jo = jasonArray.getJSONObject(index);
        }

		
	}
	
	// ////////////////////////////////////////////////////////////
	// 						helpers
	// //////////////////////////////////////////////////////////// 
	
	
}
