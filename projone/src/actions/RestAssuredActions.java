package actions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;

import helpers.SisenseFolder;
import helpers.SisenseUser;

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
import java.util.Arrays;
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
	
	public static String sisenseAccessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiNWQwYTg2ZDZlYjczOGUyOTBjMTY0YmZhIiwiYXBpU2VjcmV0IjoiOWE2OWEzNTctNDM4OS1hN2ZiLWQwNjEtMjhhNWRlMjc4ZGQxIiwiaWF0IjoxNTYxNjY4MTAwfQ.tPKyPHcqHldSDnyvrJr7rZ3hKLfDxEzciNhyMxSJGhU";

	public static String tempString = "";
	public static List<SisenseUser> listOfSisenseUsers = new ArrayList<SisenseUser>();
	public static String mainEndpoint = "http://nadevgvbi01b.corp.tangoe.com:8081/api/v1";
	
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
	    
	    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    // 
	    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    public static Response DoGetRequestSisense(String endpoint)  // MOBPROC
	    {
	        RestAssured.defaultParser = Parser.JSON;

	        return
	        		given()
	                .header("Authorization", "Bearer " + sisenseAccessToken)	                		
	                .when().get(endpoint).then().contentType(ContentType.JSON).extract().response();
	    }

		// the URL below was found by going to deva, finding the API swagger for /authentication/login - Authenticate and receive token
		// look under "authentication" in sisense swagger page
		public static void postTokenSisense() {
			//RestAssured.baseURI ="http://nadevgvbi01a.corp.tangoe.com:8081/api/v1/authentication/login";
			//RestAssured.baseURI = "http://dc1devapp08.prod.tangoe.com:8081/api/v1/authentication/login"; // sil's mail 
			
		    String payload = "{\n" +
		            "  \"username\": \"bob.lichtenfels@tangoe.com\",\n" +
		            "  \"password\": \"Tangoe1!\"\n" +
		            "}";			
			
		    ShowText(payload);
		    Response response = 
		            given().urlEncodingEnabled(true)
		            .contentType(ContentType.JSON)
		            .body(payload)
		            //.post("http://nadevgvbi01a.corp.tangoe.com:8081/api/v1/authentication/login")
		            .post(mainEndpoint + "/authentication/login")
		            .then()
		            //.statusCode(200)
		            .extract()
		            .response();
		    
			System.out.println(response.getStatusCode());
			JsonPath jsonPathEvaluator = response.jsonPath();
			System.out.println(jsonPathEvaluator.get());
			ShowText(jsonPathEvaluator.get("access_token").toString());
		}
		
		public static void getSisenseUsers() {
	        int cntr = 0;
	        int expectedListSize = 0;
			// Response response = DoGetRequestSisense("http://nadevgvbi01a.corp.tangoe.com:8081/api/v1/users");
			Response response = DoGetRequestSisense(mainEndpoint + "/users");
			
	        Assert.assertEquals(response.getStatusCode(), 200); // verify 200 response 
			List<String> jsonResponse = response.jsonPath().getList("$"); // get number of individual items (blocks) of data in response.
	        expectedListSize = jsonResponse.size();
			
	        List<String> listOfUserNames = setupStringListFromResponseAndJsonSelector(response, "userName"); // make an API request for user names and receive a list of user names 
	        Assert.assertTrue(listOfUserNames.size() == expectedListSize); // verify list size 
	        List<String> listOfIds = setupStringListFromResponseAndJsonSelector(response, "_id"); // make an API request for user ids and receive a list of user ids	        
	        Assert.assertTrue(listOfIds.size() == expectedListSize);

	        
	        for(int x = 0; x < expectedListSize; x++) {
	        	SisenseUser user = new SisenseUser(listOfUserNames.get(x), listOfIds.get(x));
	        	listOfSisenseUsers.add(user);
	        }
	        
	        for(SisenseUser user : listOfSisenseUsers) {
	        	user.show();
	        }
		}

		public static void getFolders() {
	        int cntr = 0;
	        int expectedListSize = 0;
	        List<SisenseFolder> listOfTopFolders = new ArrayList<SisenseFolder>();
	        List<SisenseFolder> listOfChildFolders = new ArrayList<SisenseFolder>();
	        
			Response response = DoGetRequestSisense(mainEndpoint + "/folders");
			
	        Assert.assertEquals(response.getStatusCode(), 200); // verify 200 response 
			List<String> jsonResponse = response.jsonPath().getList("$"); // get number of individual items (blocks) of data in response.
	        expectedListSize = jsonResponse.size();
	        System.out.println(expectedListSize);
	        
	        List<String> listOfNames = setupStringListFromResponseAndJsonSelector(response, "name"); // make an API request for user names and receive a list of user names
	        List<String> listOfOids = setupStringListFromResponseAndJsonSelector(response, "oid"); // make an API request for user names and receive a list of user names
	        List<String> listOfparentIds = setupStringListFromResponseAndJsonSelector(response, "parentId"); // make an API request for user names and receive a list of user names

	        Assert.assertTrue(listOfNames.size() == expectedListSize);
	        Assert.assertTrue(listOfOids.size() == expectedListSize);
	        Assert.assertTrue(listOfparentIds.size() == expectedListSize);
	        
	        // store all folders except root folder
	        for(int x = 0; x < expectedListSize; x++) {
	        	if(!listOfNames.get(x).equals("rootFolder")) {
	        		if(listOfparentIds.get(x).equals("null")) {
	        			listOfTopFolders.add(new SisenseFolder(listOfparentIds.get(x), listOfOids.get(x), listOfNames.get(x)));
	        		}
	        		else {
	        			listOfChildFolders.add(new SisenseFolder(listOfparentIds.get(x), listOfOids.get(x), listOfNames.get(x)));
	        		}
	        	}
	        }
	        	
	        //ShowText("Top ------------------");
	        //for(SisenseFolder folder : listOfTopFolders) {folder.Show();}
	        //ShowText("Below ------------------");
	        //for(SisenseFolder folder : listOfChildFolders) {folder.Show();}
	        
	        int level = 0;
	        String oidCouldBeParent = "";
	        String oidFolderName = "";
	        
	        for(SisenseFolder folder : listOfTopFolders) {
	        	oidCouldBeParent = folder.m_oid; // set current oId to find child's for to the top folder
	        	oidFolderName = folder.m_FolderName; // set current oId to find child's for to the top folder	        	
	        	
	        	// this goes through the child folders for the current top folder
	        	for(SisenseFolder innerFolder : listOfChildFolders) {
	        		innerFolder.doExternalQuery(level, folder);
	        	}

	        	
	        	// now go through the child nodes. get the oId for each child node and see if any other child nodes inherit from it.  
	        	for(SisenseFolder childFolder : listOfChildFolders) {
	        		oidCouldBeParent = childFolder.m_oid;
	        		oidFolderName = childFolder.m_FolderName;
	        		level++;
	        		for(SisenseFolder innerchildFolders : listOfChildFolders) {
	        			// innerchildFolders.doExternalQuery(oidCouldBeParent, oidFolderName, level); // FIX !!!!!!
		        	}
	        	}
	        }
	        
	        ShowText("Final ------------------");
	        for(SisenseFolder folder : listOfChildFolders) {folder.Show();}
		}
		
		public static void filterOnUser() { // zz
			String userNameIn = "bob.lichtenfels@tangoe.com";

			ShowText("Name to filter on: " + userNameIn);
			
			Response response = DoGetRequestSisense(mainEndpoint +  "/users?userName=" + userNameIn);
			System.out.println(response.getStatusCode());
			
			System.out.println(response.body().asString());
		}
		
		// NOTE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// String usernames = response.jsonPath().getString("username[0]");
		
		public static List<String> setupStringListFromResponseAndJsonSelector(Response response, String jsonSelector) {
			int cntr = 0;
	        tempString = response.jsonPath().getString(jsonSelector).replace("[","").replace("]", ""); // remove leading and trailing brackets.
	        String[] usersNames =  tempString.split(",");  // put in string array
	        for(String string :usersNames) { // trim spaces
	        	usersNames[cntr++] = string.trim();
	        }
	        return Arrays.asList(usersNames);
		}
		
		public static void ShowList(List<String> listIn) {
			for(String str : listIn) {ShowText(str);}
		}
}
