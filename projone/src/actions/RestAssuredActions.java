package actions;

import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
//import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import tests.BaseSelenium;

import static org.hamcrest.Matchers.*;

import java.util.List;



//import io.restassured.specification.RequestSpecification;

// import io.restassured.module.jsv.JsonSchemaValidator.*; // json schema validation

// https://www.toolsqa.com/rest-assured/configure-eclipse-with-rest-assured/
// https://artoftesting.com/automationTesting/restAPIAutomationGetRequest.html -- simple test
// https://github.com/rest-assured/rest-assured/wiki/GettingStarted#non-maven-users - see 'Non-maven users'
public class RestAssuredActions extends BaseSelenium 
{
		public static void SanityCheck() throws JSONException {
			
			//make get request to fetch capital of norway
			Response resp = get("http://restcountries.eu/rest/v1/name/norway");
			
			System.out.println(resp.getStatusCode());
			
			//Fetching response in JSON
			JSONArray jsonResponse = new JSONArray(resp.asString());
			
			//Fetching value of capital parameter
			String capital = jsonResponse.getJSONObject(0).getString("capital");
			
			//Asserting that capital of Norway is Oslo
			Assert.assertEquals(capital, "Oslo");
		}
		
		// https://www.testingexcellence.com/parse-json-response-rest-assured/ -- also see Related: sending a post. 
		// https://www.testingexcellence.com/rest-assured-post-request/ -- this is post
		public static void CollectionWork()
		{
	        Response response = doGetRequest("https://jsonplaceholder.typicode.com/users");
	        List<String> jsonResponse = response.jsonPath().getList("$");
	        System.out.println(response.getStatusCode());
	        System.out.println(jsonResponse.size());
	        
	        String usernames = response.jsonPath().getString("username");
	        System.out.println(usernames);
	        String streets = response.jsonPath().getString("address.street");	
	        String  names = response.jsonPath().getString("company.name");     
	        System.out.println(streets);
	        System.out.println(names);
	        
	        
		}
		
		

		
		// /////////////////////////////////////////////////////////////////////////////////
		// 								helpers
		// ///////////////////////////////////////////////////////////////////////////////// 
		
		
		// get helper
	    public static Response doGetRequest(String endpoint) 
	    {
	        RestAssured.defaultParser = Parser.JSON;

	        return
	                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
	                        when().get(endpoint).
	                        then().contentType(ContentType.JSON).extract().response();
	    }

		
		
		
		
		
		
		
		
		
		
		
}
