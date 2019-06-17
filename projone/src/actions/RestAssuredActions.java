package actions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
//import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tests.BaseSelenium;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



//import io.restassured.specification.RequestSpecification;

// import io.restassured.module.jsv.JsonSchemaValidator.*; // json schema validation

// https://www.toolsqa.com/rest-assured/configure-eclipse-with-rest-assured/
// https://artoftesting.com/automationTesting/restAPIAutomationGetRequest.html -- simple test
// https://github.com/rest-assured/rest-assured/wiki/GettingStarted#non-maven-users - see 'Non-maven users'
public class RestAssuredActions extends BaseSelenium 
{
	public static Map<String , Object > parametersMap = new HashMap<String,Object>();
	
	public static String accessToken = "MFFIZ7gy7B9BVgQStf7nBhljY5ml";
	
	public static void SanityCheck() throws JSONException {
			
		//make get request to fetch capital of norway
		Response resp = get("http://restcountries.eu/rest/v1/name/norway");
			
		System.out.println(resp.getStatusCode());
			
		//Fetching response in JSON
		JSONArray jsonResponse = new JSONArray(resp.asString());
			
		//Fetching value of capital parameter
		String capital = jsonResponse.getJSONObject(0).getString("capital");
			
		//Asserting that capital of Norway is Oslo
		// Assert.assertEquals(capital, "Oslo");
	}
		
		// https://www.testingexcellence.com/parse-json-response-rest-assured/ 
		// https://www.testingexcellence.com/rest-assured-post-request/ -- this is post
		// https://www.toolsqa.com/rest-assured/post-request-using-rest-assured/ another post
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

		// https://www.testingexcellence.com/rest-assured-post-request/ -- NOTE !!!!! ----- good page that also includes how to send post body.
		public static void PostToken() throws JSONException
		{
			RestAssured.baseURI ="https://oauthqa.tangoe.com/as/token.oauth2";

			Response response = 
			
			 given().urlEncodingEnabled(true)
			.param("client_id", "392d0283466b4e00be8e8b8062db5f9f")
			.param("client_secret", "3c684c3220e740ef92E7177A32AE978D")
			.param("grant_type", "password")
			.param("scope", "MOBPROC")
			.param("username", "command://ana.pace.xx1#xx1")
			.param("password", "tngo777")
			.post(baseURI)
            .then()
            .contentType(ContentType.JSON)
            //.statusCode(200) // gives error
            .extract()
            .response();

			Assert.assertEquals(response.getStatusCode(), 200);

			//response.print(); // see all

			JsonPath jsonPathEvaluator = response.jsonPath();
			System.out.println(jsonPathEvaluator.get("access_token").toString());
			accessToken = jsonPathEvaluator.get("access_token").toString();
			System.out.println("Done with token");
			
	    	/*
	    	Map<String,Object> headerMap = new HashMap<String,Object>();
	    	headerMap.put("client_id", "392d0283466b4e00be8e8b8062db5f9f");
	    	headerMap.put("client_secret", "3c684c3220e740ef92E7177A32AE978D");
	    	headerMap.put("grant_type", "password");
	    	headerMap.put("scope", "MOBPROC");
	    	headerMap.put("username", "command://ana.pace.xx1#xx1");	    
	    	headerMap.put("password", "tngo777");	    	
			*/
		}
		
		// the URL below was found by going to deva, finding the API swagger for /authentication/login - Authenticate and receive token
		// look under "authentication" in swagger
		public static void postTokenSisense() {
			RestAssured.baseURI ="http://nadevgvbi01a.corp.tangoe.com:8081/api/v1/authentication/login";
			//RestAssured.baseURI = "http://dc1devapp08.prod.tangoe.com:8081/api/v1/authentication/login"; // sil's mail 
			

			Response response = 
				
			 given().urlEncodingEnabled(true)
			.param("username", "bob.lichtenfels")
			.param("password", "Tangoe1!")
			.post(baseURI)
	        .then()
	        .contentType(ContentType.JSON)
	        //.statusCode(200) // gives error
	        .extract()
	        .response();
			
			System.out.println(response.getStatusCode());
			
			System.out.println(response);
			JsonPath jsonPathEvaluator = response.jsonPath();
			System.out.println(jsonPathEvaluator.get());

		}
		
		public static void Mobproc()
		{
			Response response = DoGetRequestMobproc("https://tngo-qa-mobproc.cloudhub.io/mobproc/v1.qa/regions?type=PROCUREMENT");
			Assert.assertEquals(response.getStatusCode(), 200);
			
			// System.out.println(response.print()); // this will show everything in response in json format. EVERYTHING !!!
			
			// https://stackoverflow.com/questions/21166137/rest-assured-is-it-possible-to-extract-value-from-request-json
			JsonPath jsonPathEvaluator = response.jsonPath();
			System.out.println(jsonPathEvaluator.get("regions.countryCode").toString());
			System.out.println(jsonPathEvaluator.get("regions[3].countryCode").toString());			
			System.out.println(jsonPathEvaluator.get("regions.currency.code").toString());			
		}

		public static void MobprocTwo()
		{
			Response response = DoGetRequestMobprocHeaderList("https://tngo-qa-mobproc.cloudhub.io/mobproc/v1.qa/regions?type=PROCUREMENT", CreateHeadersMap());
			Assert.assertEquals(response.getStatusCode(), 200);
			
			// System.out.println(response.print()); // this will show everything in response in json format. EVERYTHING !!!
			
			// https://stackoverflow.com/questions/21166137/rest-assured-is-it-possible-to-extract-value-from-request-json
			JsonPath jsonPathEvaluator = response.jsonPath();
			System.out.println(jsonPathEvaluator.get("regions.countryCode").toString());
			System.out.println(jsonPathEvaluator.get("regions[3].countryCode").toString());			
			System.out.println(jsonPathEvaluator.get("regions.currency.code").toString());			
		}
		
		public static void MobprocAssetsDevices()	{
			//Response response = DoGetRequestMobprocHeaderList("https://tngo-qa-mobproc.cloudhub.io/mobproc/v1.qa/assets/devices?limit=10&offset=0", CreateHeadersMap());
			
			//String params = "offset=0,limit=5,companyEmployeeId=3174020727"; // setup query parameters
			String params = "offset=100,limit=10"; // setup query parameters			
			
			
			// make call by getting always-needed header parameters and parameters setup in params variable. 
			Response response = DoGetRequestMobprocQueryHeaderList("https://tngo-qa-mobproc.cloudhub.io/mobproc/v1.qa/assets/devices", CreateHeadersMap(), CreateQueryParamsMap(params));
			
			Assert.assertEquals(response.getStatusCode(), 200);

			JsonPath jsonPathEvaluator = response.jsonPath();
			System.out.println(jsonPathEvaluator.get("devices._meta.totalCount").toString());
			System.out.println(jsonPathEvaluator.get("devices.items.employee.companyEmployeeId").toString());
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

	    public static Response DoGetRequestMobproc(String endpoint)  // MOBPROC
	    {
	        RestAssured.defaultParser = Parser.JSON;

	        return
	                 given()//.header("Content-Type", ContentType.JSON)
	                	    //.header("Accept", ContentType.JSON)
	                		.header("Authorization", "Bearer " + accessToken)	                		
	                		.header("X-TNGO-TENANT", "XX1")
	                		.header("client_id", "392d0283466b4e00be8e8b8062db5f9f")
	                		.header("client_secret", "3c684c3220e740ef92E7177A32AE978D")
	                		.when().get(endpoint).
	                        then().contentType(ContentType.JSON).extract().response();
	    }

	    public static Response DoGetRequestMobprocHeaderList(String endpoint, Map<String, Object> headersMap)  // MOBPROC 
	    {
	        RestAssured.defaultParser = Parser.JSON;

	        return
	                 given()//.header("Content-Type", ContentType.JSON)
	                	    //.header("Accept", ContentType.JSON)
	                		.headers(headersMap)	                		
	                		.when().get(endpoint).
	                        then().contentType(ContentType.JSON).extract().response();
	    }

	    public static Response DoGetRequestMobprocQueryHeaderList(String endpoint, Map<String, Object> headersMap, Map<String, Object> querysMap)  // MOBPROC 
	    {
	        RestAssured.defaultParser = Parser.JSON;

	        return
	                 given()//.header("Content-Type", ContentType.JSON)
	                	    //.header("Accept", ContentType.JSON)
	                		.headers(headersMap)	                		
	                		.params(querysMap)
	                		.when().get(endpoint).
	                        then().contentType(ContentType.JSON).extract().response();
	    }
	    
	    public static Map<String, Object> CreateHeadersMap()
	    {
	    	Map<String,Object> headerMap = new HashMap<String,Object>();
	    	headerMap.put("Authorization", "Bearer " + accessToken);
	    	headerMap.put("X-TNGO-TENANT", "XX1");
	    	headerMap.put("client_id", "392d0283466b4e00be8e8b8062db5f9f");
	    	headerMap.put("client_secret", "3c684c3220e740ef92E7177A32AE978D");
	    	return headerMap;
	    }
	    
	    public static Map<String, Object> CreateQueryParamsMap()
	    {
	    	Map<String,Object> paramsMap = new HashMap<String,Object>();
	    	paramsMap.put("offset", "0");
	    	paramsMap.put("limit", "5");
	    	return paramsMap;
	    }
	    
	    // return a map of the parameters passed in.
	    public static Map<String, Object> CreateQueryParamsMap(String params)
	    {
	    	String [] paramsArray = params.split(",");
	    	Map<String,Object> paramsMap = new HashMap<String,Object>();	    	
	    	
	    	for(String str : paramsArray)
	    	{
	    		ShowText(str);
	    		paramsMap.put(str.split("=")[0],str.split("=")[1]);
	    	}
	    	return paramsMap;
	    }
}
