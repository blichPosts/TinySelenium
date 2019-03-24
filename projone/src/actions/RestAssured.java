package actions;

import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

// import io.restassured.module.jsv.JsonSchemaValidator.*; // json schema validation

// https://www.toolsqa.com/rest-assured/configure-eclipse-with-rest-assured/
// https://artoftesting.com/automationTesting/restAPIAutomationGetRequest.html -- simple test
public class RestAssured 
{
	public class RestTest {

		//@Test
		public void getRequestFindCapital() throws JSONException {
			
			//make get request to fetch capital of norway
			//Response resp = get("http://restcountries.eu/rest/v1/name/norway");
			
			//Fetching response in JSON
			//JSONArray jsonResponse = new JSONArray(resp.asString());
			
			//Fetching value of capital parameter
			//String capital = jsonResponse.getJSONObject(0).getString("capital");
			
			//Asserting that capital of Norway is Oslo
			//Assert.assertEquals(capital, "Oslo");
		}
		
	}
	
}
