package actions;

import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;

import helpers.Dashboard;
import helpers.DataSet;
import helpers.LevelInfo;
import helpers.SisenseFolder;
import helpers.SisenseUser;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
//import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import tests.BaseSelenium;
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
	
	public static String sisenseAccessToken = "";


	
	
	
	
	
	public static String tempString = "";
	public static String tempParentFolder = "";
	public static List<SisenseUser> listOfSisenseUsers = new ArrayList<SisenseUser>();
	public static String mainEndpoint = "http://nadevgvbi01b.corp.tangoe.com:8081/api/v1"; // dev
	public static String sisenseMainEndpoint = "http://nadevgvbi01b.corp.tangoe.com:8081/api/v1"; // dev
	
	
	//public static String mainEndpoint = "http://naqagvbi01b.corp.tangoe.com:8081/api/v1"; // qa
	
	public static List<String> listOfDashBoardNames = new ArrayList<>();
	public static List<String> listOfDashBoardOids = new ArrayList<>();
	public static List<Dashboard> listOfDashBoards = new ArrayList<>();
	public static List<DataSet> listOfDataSetObjects = new ArrayList<>(); // zzz
	
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

	    // for token logout
	    public static Response DoGetRequestSisenseTokenLogout(String endpoint)  
	    {
	        RestAssured.defaultParser = Parser.JSON;

	        return
	        		given()
	                .header("Authorization", "Bearer " + sisenseAccessToken)	                		
	        		.when().get(endpoint).then().extract().response();
	    }

	    
	    
	    
	    public static Response DoGetRequestSisenseBoolean(String endpoint)  // MOBPROC
	    {
	        RestAssured.defaultParser = Parser.JSON;

	        Response resp = null;
	        resp = given().header("Authorization", "Bearer " + sisenseAccessToken).when().get(endpoint).then().contentType(ContentType.JSON).extract().response();
	        return resp;
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
		
		public static void postTokenSisenseForSpecifiedUser(String specifiedUser) {
			//RestAssured.baseURI ="http://nadevgvbi01a.corp.tangoe.com:8081/api/v1/authentication/login";
			//RestAssured.baseURI = "http://dc1devapp08.prod.tangoe.com:8081/api/v1/authentication/login"; // sil's mail 
			
		    String payload = "{\n" +
		            "  \"username\": \"" + specifiedUser + "\",\n" +
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
			sisenseAccessToken = jsonPathEvaluator.get("access_token").toString();
		}
		
		// bladd special for admin user.
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public static List<Dashboard> getSpecificUserSisenseDashboardDataAdmin() { 
			List<String> tempList;
			Response response = DoGetRequestSisense(sisenseMainEndpoint + "/dashboards"); // get dash board oids for user token 
	        Assert.assertEquals(response.getStatusCode(), 200); // verify 200 response
	        
	        // get the dash board oids out of the response. 
	        listOfDashBoardOids = setupStringListFromResponseAndJsonSelector(response, "oid");

	        if(listOfDashBoardOids == null) {
	        	return null;
	        }
	        else {
		        // go through the oids and get 'title' field (dash board name). add each name to dashboard list 
		        for(String oid : listOfDashBoardOids) { 
		        	response = DoGetRequestSisense(sisenseMainEndpoint + "/dashboards/"   + oid + "?fields=title"); // query on a dash board oid and get back name (response called title) section into response  
		        	Assert.assertTrue(setupStringListFromResponseAndJsonSelector(response, "title").size() == 1); // should only get one name (response called title) section back 
		        	tempString = setupStringListFromResponseAndJsonSelector(response, "title").toString();  // get the name (response called title) out of response and convert to string. 
		        	Assert.assertTrue(tempString != null);
		        	listOfDashBoardNames.add(tempString.replace("[","").replace("]","") ); // add dash title/name and to dash board names list after removing list brackets.
		        }
		        
		        Assert.assertTrue(listOfDashBoardOids.size() == listOfDashBoardNames.size()); // extra sanity
		        
		        // create list of dash board objects.
		        int cntr = 0; 
		        for(String dashBoardName: listOfDashBoardNames) {
		        	listOfDashBoards.add(new Dashboard(dashBoardName, listOfDashBoardOids.get(cntr++)));
		        }

		        // add widget title names for each dash board.
		        for(Dashboard dBoard : listOfDashBoards) {
		        	response = DoGetRequestSisense(sisenseMainEndpoint + "/dashboards/" + dBoard.m_Oid + "/widgets?fields=title"); //
		        	tempList = setupStringListFromResponseAndJsonSelector(response, "title");
		        	if(tempList != null) {
			        	for(String titleName : tempList) {
			        		dBoard.addTitle(titleName);
			        	}
		        	}
		        }
	        }
	        return listOfDashBoards;
		}	

		
		
		
		public static void getSpecificUserDashboardData() { // zz
			List<String> tempList;
			Response response = DoGetRequestSisense(mainEndpoint + "/dashboards"); // get dash board oids for user token 
	        Assert.assertEquals(response.getStatusCode(), 200); // verify 200 response
	        
	        // get the dash board oids only out of the response. 
	        listOfDashBoardOids = setupStringListFromResponseAndJsonSelector(response, "oid");

	        if(listOfDashBoardOids == null) {
	        	
	        }
	        else {
		        // go through the oids and get 'title' field. this is the dash board name. add each name and corresto the list 
		        for(String oid : listOfDashBoardOids) { 
		        	response = DoGetRequestSisense(mainEndpoint + "/dashboards/"   + oid + "?fields=title"); 
		        	Assert.assertTrue(setupStringListFromResponseAndJsonSelector(response, "title").size() == 1); // should only get one item back 
		        	tempString = setupStringListFromResponseAndJsonSelector(response, "title").toString(); 
		        	listOfDashBoardNames.add(tempString.replace("[","").replace("]","") ); // add dash name and oid to dash board list
		        }
		        
		        int cntr = 0;
		        for(String str: listOfDashBoardNames) {
		        	listOfDashBoards.add(new Dashboard(str, listOfDashBoardOids.get(cntr++)));
		        }

		        for(Dashboard dBoard : listOfDashBoards) {
		        	response = DoGetRequestSisense(mainEndpoint + "/dashboards/" + dBoard.m_Oid + "/widgets?fields=title"); //
		        	tempList = setupStringListFromResponseAndJsonSelector(response, "title");
		        	System.out.println(tempList);
		        	if(tempList != null) {
			        	for(String str : tempList) {
			        		dBoard.addTitle(str);
			        	}
		        	}
		        }
	        }
	        for(Dashboard dBoard : listOfDashBoards) {
	        	dBoard.Show();
	        }
		}
		
		public static void logoutSisenseToken() {
			Response response = DoGetRequestSisenseTokenLogout(mainEndpoint + "/authentication/logout"); 
			Assert.assertTrue(response.getStatusCode() == 200);

			
			
			//Headers allHeaders = response.headers();
			// Iterate over all the Headers
			//for(Header header : allHeaders){
			//	System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
			//}
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

		public static void getFoldersTwo() {

			// http://nadevgvbi01b.corp.tangoe.com:8081/api/v1/folders/5d1532a50df2540edcce380e/subtree?structure=tree
	        int expectedListSize = 0;
	        List<SisenseFolder> listOfTopFolders = new ArrayList<SisenseFolder>();
	        //List<SisenseFolder> listOfChildFolders = new ArrayList<SisenseFolder>();
	        
			Response responseFolders = DoGetRequestSisense(mainEndpoint + "/folders");
			
	        Assert.assertEquals(responseFolders.getStatusCode(), 200); // verify 200 response 
			List<String> jsonResponse = responseFolders.jsonPath().getList("$"); // get number of individual items (blocks) of data in response.
	        expectedListSize = jsonResponse.size();
	        
	        List<String> listOfNames = setupStringListFromResponseAndJsonSelector(responseFolders, "name");
	        List<String> listOfOids = setupStringListFromResponseAndJsonSelector(responseFolders, "oid");
	        List<String> listOfparentIds = setupStringListFromResponseAndJsonSelector(responseFolders, "parentId"); 

	        Assert.assertTrue(listOfNames.size() == expectedListSize);
	        Assert.assertTrue(listOfOids.size() == expectedListSize);
	        Assert.assertTrue(listOfparentIds.size() == expectedListSize);

	        // store all folders except root folder
	        for(int x = 0; x < expectedListSize; x++) {
	        	if(!listOfNames.get(x).equals("rootFolder")) {
	        		if(listOfparentIds.get(x).equals("null")) {
	        			// listOfTopFolders.add(new SisenseFolder(listOfparentIds.get(x), listOfOids.get(x), listOfNames.get(x))); // out
	        		}
	        	}
	        }

	        
	        // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        // 											START
	        // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        int level = 1;
	        Response responseAncestors;
	        Response subTree;;
	        String parentFolder = ""; 

	        List<String> tempListOfFolderOids = new ArrayList<String>();
	        List<String> tempListOfFolderNames = new ArrayList<String>();	        
	        List<SisenseFolder> sisnseFoldersTreeStructureInOrder = new ArrayList<SisenseFolder>();
	        
	        // call into top of folders, get folder tree info for all folders, and put info into 'responseFolders' response
	        responseFolders = DoGetRequestSisense(mainEndpoint + "/folders" + "?structure=tree");  
	        ShowText("-----------");
	        // get all oids and names into list for current level in 'responseFolders'
        	//System.out.println(setupStringListFromResponseAndJsonSelector(responseFolders, "folders.oid"));
        	//System.out.println(setupStringListFromResponseAndJsonSelector(responseFolders, "folders.name"));
			//Response responseAncestors = DoGetRequestSisense(mainEndpoint + "/folders/" + "5d163415758ceb268485f8f0" + "/ancestors?structure=tree");
        	//System.out.println("5d1532a50df2540edcce380e " + setupStringListFromResponseAndJsonSelector(responseAncestors, "name"));
        	
        	tempListOfFolderOids = setupStringListFromResponseAndJsonSelector(responseFolders, "folders.oid");
        	tempListOfFolderNames = setupStringListFromResponseAndJsonSelector(responseFolders, "folders.name");
        	Assert.assertTrue(tempListOfFolderNames.size() == tempListOfFolderOids.size());

        	// NOTE !! responseAncestors needs name change
        	for(int x = 0; x < tempListOfFolderNames.size(); x++) {
        		ShowText("Folder Name = " + tempListOfFolderNames.get(x));
        		ShowText("Oid = " + tempListOfFolderOids.get(x));        		
        		responseAncestors = DoGetRequestSisenseBoolean(mainEndpoint + "/folders/" + tempListOfFolderOids.get(x) + "/ancestors?structure=tree"); // get ancestor 
        		System.out.println(setupStringListFromResponseAndJsonSelector(responseAncestors, "name")); // show ancestor
        		//responseAncestors = DoGetRequestSisenseBoolean(mainEndpoint + "/folders/" + tempListOfFolderOids.get(x) + "/subtree?structure=tree"); // get child
        		//System.out.println(setupStringListFromResponseAndJsonSelector(responseAncestors, "folders.name")); // show child 
        	}


        	ShowText("-----------");
        	/*
        	System.out.println(setupStringListFromResponseAndJsonSelector(responseFolders, "folders.folders.oid"));
        	System.out.println(setupStringListFromResponseAndJsonSelector(responseFolders, "folders.folders.name"));
			responseAncestors = DoGetRequestSisense(mainEndpoint + "/folders/" + "5d163415758ceb268485f8f0" + "/ancestors?structure=tree");
        	System.out.println("5d163415758ceb268485f8f0 " + setupStringListFromResponseAndJsonSelector(responseAncestors, "folders.name"));
			responseAncestors = DoGetRequestSisense(mainEndpoint + "/folders/" + "5d1654fa758ceb268485f8f4" + "/ancestors?structure=tree");
        	System.out.println("5d1654fa758ceb268485f8f4 " + setupStringListFromResponseAndJsonSelector(responseAncestors, "folders.name"));
        	*/ 
        	

        	tempListOfFolderOids = setupStringListFromResponseAndJsonSelector(responseFolders, "folders.folders.oid");
        	tempListOfFolderNames = setupStringListFromResponseAndJsonSelector(responseFolders, "folders.folders.name");
        	Assert.assertTrue(tempListOfFolderNames.size() == tempListOfFolderOids.size());

        	for(int x = 0; x < tempListOfFolderNames.size(); x++) {
        		ShowText("Folder Name = " + tempListOfFolderNames.get(x));
        		ShowText("Oid = " + tempListOfFolderOids.get(x));
        		responseAncestors = DoGetRequestSisenseBoolean(mainEndpoint + "/folders/" + tempListOfFolderOids.get(x) + "/ancestors?structure=tree"); // get ancestor
        		System.out.println("Ancestror " + setupStringListFromResponseAndJsonSelector(responseAncestors, "folders.name")); // show ancestor
            	//responseAncestors = DoGetRequestSisenseBoolean(mainEndpoint + "/folders/" + tempListOfFolderOids.get(x) + "/subtree?structure=tree"); // get child        			
            	//System.out.println(responseAncestors);
            	//responseAncestors = DoGetRequestSisenseBoolean(mainEndpoint + "/folders/" + tempListOfFolderOids.get(x) + "/subtree?structure=flat"); // get child        			
            	//System.out.println(responseAncestors);
            	
        		//System.out.println(responseAncestors);
        	}
        	
	        ShowText("-----------");
	        /*
	        System.out.println(setupStringListFromResponseAndJsonSelector(responseFolders, "folders.folders.folders.oid"));
        	System.out.println(setupStringListFromResponseAndJsonSelector(responseFolders, "folders.folders.folders.name"));
			responseAncestors = DoGetRequestSisense(mainEndpoint + "/folders/" + "5d17a23d758ceb268485f8f6" + "/ancestors?structure=tree");
        	System.out.println("5d17a23d758ceb268485f8f6 " + setupStringListFromResponseAndJsonSelector(responseAncestors, "folders.folders.name"));
			responseAncestors = DoGetRequestSisense(mainEndpoint + "/folders/" + "5d16408a758ceb268485f8f2" + "/ancestors?structure=tree");
        	System.out.println("5d16408a758ceb268485f8f2 " + setupStringListFromResponseAndJsonSelector(responseAncestors, "folders.folders.name"));
			responseAncestors = DoGetRequestSisense(mainEndpoint + "/folders/" + "5d18cfda758ceb268485f8f8" + "/ancestors?structure=tree");
        	System.out.println("5d18cfda758ceb268485f8f8 " + setupStringListFromResponseAndJsonSelector(responseAncestors, "folders.folders.name"));
        	*/
        	
        	tempListOfFolderOids = setupStringListFromResponseAndJsonSelector(responseFolders, "folders.folders.folders.oid");
        	tempListOfFolderNames = setupStringListFromResponseAndJsonSelector(responseFolders, "folders.folders.folders.name");
        	Assert.assertTrue(tempListOfFolderNames.size() == tempListOfFolderOids.size());

        	for(int x = 0; x < tempListOfFolderNames.size(); x++) {
        		ShowText("Folder Name = " + tempListOfFolderNames.get(x));
        		ShowText("Oid = " + tempListOfFolderOids.get(x));
        		responseAncestors = DoGetRequestSisenseBoolean(mainEndpoint + "/folders/" + tempListOfFolderOids.get(x) + "/ancestors?structure=tree"); // get ancestor
        		System.out.println("Ancestror " + setupStringListFromResponseAndJsonSelector(responseAncestors, "folders.folders.name")); // show ancestor
            	//responseAncestors = DoGetRequestSisenseBoolean(mainEndpoint + "/folders/" + tempListOfFolderOids.get(x) + "/subtree?structure=tree"); // get child        			
            	//System.out.println(responseAncestors);
            	//responseAncestors = DoGetRequestSisenseBoolean(mainEndpoint + "/folders/" + tempListOfFolderOids.get(x) + "/subtree?structure=flat"); // get child        			
            	//System.out.println(responseAncestors);
            	
        		//System.out.println(responseAncestors);
        	}
        	
        	
	        ShowText("-----------");
        	System.out.println(setupStringListFromResponseAndJsonSelector(responseFolders, "folders.folders.folders.folders.oid"));
		}
		
		public static void getFoldersThree() { 
			int cntr = 0;
			int folderCounter = 0;
			String oidPath = "folders.oid";
			String namePath = "folders.name";
			String ancestorPath = "name";
	        Response tempResponse;

	        
	        List<String> tempListOfFolderOids = new ArrayList<String>();
	        List<String> tempListOfFolderNames = new ArrayList<String>();	        

	        // Main call to get all folders.
	        Response responseFolders = DoGetRequestSisense(mainEndpoint + "/folders" + "?structure=tree"); // get all sisense folders into tree structure
			
			do {
				//ShowText("Oid path=" + oidPath + " Folder name Path=" + namePath + " Ancestor Path=" + ancestorPath); // this shows changing variables on each loop
	        	tempListOfFolderOids = setupStringListFromResponseAndJsonSelector(responseFolders, oidPath);
	        	tempListOfFolderNames = setupStringListFromResponseAndJsonSelector(responseFolders, namePath);
	        	Assert.assertTrue(tempListOfFolderNames.size() == tempListOfFolderOids.size());
			
	        	ShowText("--------------------- Level = " + cntr);
	        	System.out.println("Folder list back " + tempListOfFolderNames);
	        	
	        	if(isAllSpaces(tempListOfFolderNames.toString().replace("[", "" ).replace("]", "").replace(",",""))) {
	        		break;
	        	}
	        		
	        	for(int x = 0; x < tempListOfFolderNames.size(); x++) {
	        		if(tempListOfFolderNames.get(x).length() == 0) {      
	        			continue;
	        		}
	        		ShowText("Folder Name = " + tempListOfFolderNames.get(x));
	        		folderCounter ++;
	        		
	        		tempResponse = DoGetRequestSisense(mainEndpoint + "/folders/" + tempListOfFolderOids.get(x) + "/ancestors?structure=tree"); // get ancestor folders for this oid
	        		System.out.println("Parent " + setupStringListFromResponseAndJsonSelector(tempResponse, ancestorPath)); // show ancestor folder
	        	}
	        	
				// update to go one level below the last level queried
				oidPath = "folders." + oidPath;
				namePath = "folders." + namePath;
				ancestorPath = "folders." + ancestorPath;
				
				System.out.println("Counter finished = " + cntr);
				cntr++;
			}  while (cntr < 17);
			
			System.out.println("Total folder count = " +  folderCounter);
		}
		

		public static void getFoldersFourBuildList() {  
			int levelCntr = 0;
			int folderCounter = 0;
			String oidPath = "folders.oid";
			String namePath = "folders.name";
			String ancestorPath = "name";
			String tempParentFolder = "";
	        Response tempResponse;

	        List<LevelInfo> levelInfoList = new ArrayList<LevelInfo>();
	        List<String> tempListOfFolderOids = new ArrayList<String>();
	        List<String> tempListOfFolderNames = new ArrayList<String>();	        

	        // Main call to get all folders.
	        Response responseFolders = DoGetRequestSisense(mainEndpoint + "/folders" + "?structure=tree"); // get all sisense folders into tree structure
			
			do {
				//ShowText("Oid path=" + oidPath + " Folder name Path=" + namePath + " Ancestor Path=" + ancestorPath); // this shows changing variables on each loop
	        	tempListOfFolderOids = setupStringListFromResponseAndJsonSelector(responseFolders, oidPath);
	        	tempListOfFolderNames = setupStringListFromResponseAndJsonSelector(responseFolders, namePath);
	        	Assert.assertTrue(tempListOfFolderNames.size() == tempListOfFolderOids.size()); // sanity check
			
	        	ShowText("--------------------- Level = " + levelCntr);
	        	System.out.println("Folder list back " + tempListOfFolderNames);
	        	
	        	if(isAllSpaces(tempListOfFolderNames.toString().replace("[", "" ).replace("]", "").replace(",",""))) {
	        		break;
	        	}

	        	// create LevelInfo class for this level and add to list of LevelInfo. Pass in the index and folder names at this level. 
	        	levelInfoList.add(new LevelInfo(levelCntr, tempListOfFolderNames));
	        	
	        	// go through the list of folders and list of corresponding ods.  
	        	for(int x = 0; x < tempListOfFolderNames.size(); x++) {
	        		if(tempListOfFolderNames.get(x).length() == 0) {      
	        			continue;
	        		}
	        		ShowText("Folder Oid = " + tempListOfFolderOids.get(x));
	        		ShowText("Folder Name = " + tempListOfFolderNames.get(x));

	        		folderCounter ++;
	        		
	        		// get ancestor folders for this oid and then take out the immediate parent folder to this oid.
	        		tempResponse = DoGetRequestSisense(mainEndpoint + "/folders/" + tempListOfFolderOids.get(x) + "/ancestors?structure=tree"); 
	        		System.out.println("Parent " + setupStringListFromResponseAndJsonSelector(tempResponse, ancestorPath)); // show ancestor folder
	        		tempParentFolder =  setupStringListFromResponseAndJsonSelector(tempResponse, ancestorPath).get(0);  
	        		
	        		// add current parent folder, immediate child folder, and child oid to current level object
	        		levelInfoList.get(levelCntr).AddParentChild(tempParentFolder, tempListOfFolderNames.get(x), tempListOfFolderOids.get(x)); 
	        	}
	        	
				// update to go one level below the last level queried
				oidPath = "folders." + oidPath;
				namePath = "folders." + namePath;
				ancestorPath = "folders." + ancestorPath;
				
				System.out.println("Level counter finished = " + levelCntr);
				levelCntr++;
			}  while (levelCntr < 17);
			
			System.out.println("Total folder count = " +  folderCounter);
			System.out.println("Level info list size  = " + levelInfoList.size());
			System.out.println("\n **************************************************** ");
			for(LevelInfo lInfo : levelInfoList) {
				lInfo.Show();
			}
			
			System.out.println("------------------ COMPLETE --------------------------------");
			

			for(int x = 0; x < levelInfoList.size(); x++) {
				if((x+1) == levelInfoList.size()){
					break;
				}
				
				List<String> tempList =levelInfoList.get(x).getAllFoldersAtThisLevel();
				
				for(String str : tempList) {
					System.out.println("Folder = " + str);
					System.out.println(levelInfoList.get(x+1).getChildrenforParent(str));
				}
			}
			
			

			
		}
		
		public static boolean isAllSpaces(String strIn) {
			
			for(int x = 0; x < strIn.length(); x++) {
				if(!(strIn.charAt(x) == ' ')) {
					return false;
				}
			}
			return true;
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
	        /*
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
	        	*/
	        //ShowText("Parent folders *** "); for(SisenseFolder folder : listOfTopFolders) {folder.Show();}
	        //ShowText("Child folders below parents *** "); for(SisenseFolder folder : listOfChildFolders) {folder.Show();}
	        /*
	        for(SisenseFolder folder : listOfTopFolders) {
	        	
	        	// this goes through the child folders for the current top folder
	        	for(SisenseFolder innerFolder : listOfChildFolders) {
	        		innerFolder.doExternalQuery(folder);
	        	}

	        	
	        	// for each   
	        	for(SisenseFolder childFolder : listOfChildFolders) {
	        		for(SisenseFolder childFolderInner : listOfChildFolders) {
		        		childFolderInner.doExternalQuery(childFolder);
	        		}
	        	}
	        }
	        
	        for(SisenseFolder folder : listOfTopFolders) {folder.Show();} // show all
	        for(SisenseFolder folder : listOfChildFolders) {folder.Show();} // show all
	        */
		}
		
		public static void filterOnUser() { 
			String userNameIn = "bob.lichtenfels@tangoe.com";

			ShowText("Name to filter on: " + userNameIn);
			
			Response response = DoGetRequestSisense(mainEndpoint +  "/users?userName=" + userNameIn);
			System.out.println(response.getStatusCode());
			
			System.out.println(response.body().asString());
		}
		
		// NOTE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// String usernames = response.jsonPath().getString("username[0]");
		
		public static List<String> setupStringListFromResponseAndJsonSelector(Response response, String jsonSelector) {
			//ShowText(jsonSelector);
			int cntr = 0;
			if(response.jsonPath().getString(jsonSelector).equals("[]")){ // query found nothing
				return null;
			}
			tempString = response.jsonPath().getString(jsonSelector).replace("[","").replace("]", ""); // remove leading and trailing brackets.
	        String[] usersNames =  tempString.split(",");  // put in string array
	        for(String string :usersNames) { // trim spaces
	        	usersNames[cntr++] = string.trim();
	        }
	        return Arrays.asList(usersNames); // return as list
		}
		
		public static List<String> setupStringListFromResponseAndJsonSelectorIndex(Response response, String jsonSelector, int index) {
			//ShowText(jsonSelector);
			int cntr = 0;
			if(response.jsonPath().getString(jsonSelector).equals("[]")){ // query found nothing
				return null;
			}
			tempString = response.jsonPath().getString(jsonSelector + "[" + index  + "]").replace("[","").replace("]", ""); // remove leading and trailing brackets.
	        String[] usersNames =  tempString.split(",");  // put in string array
	        for(String string :usersNames) { // trim spaces
	        	usersNames[cntr++] = string.trim();
	        }
	        return Arrays.asList(usersNames); // return as list
		}
		
		
		
		
		public static List<String> setupStringListFromResponseAndJsonSelectorWithFailCondition(Response response, String jsonSelector) {
			ShowText(jsonSelector);
			int cntr = 0;
			try {
				tempString = response.jsonPath().getString(jsonSelector).replace("[","").replace("]", ""); // remove leading and trailing brackets.				
			}catch(Exception e) {
				return null;
			}
	        String[] usersNames =  tempString.split(",");  // put in string array
	        for(String string :usersNames) { // trim spaces
	        	usersNames[cntr++] = string.trim();
	        }
	        return Arrays.asList(usersNames);
		}
		
		public static void ShowList(List<String> listIn) {
			for(String str : listIn) {ShowText(str);}
		}
		
		public static void WorkOnElasticCubes() { // zzz
			Response response = DoGetRequestSisense(mainEndpoint + "/elasticubes/servers/next/LocalHost/Sample Healthcare");
			Assert.assertEquals(response.getStatusCode(), 200);

			List<String> listOfDataSetsNames = setupStringListFromResponseAndJsonSelector(response, "datasets.name");
			List<String> listOfDataSetsOids = setupStringListFromResponseAndJsonSelector(response, "datasets.oid");		
			
			
			
			
			
			
			
			Assert.assertTrue(listOfDataSetsNames.size() == listOfDataSetsOids.size());
			
			
			for(int x = 0; x < listOfDataSetsNames.size(); x++) {
				System.out.println("-----------------------------");
				System.out.println(listOfDataSetsNames.get(x));
				System.out.println(setupStringListFromResponseAndJsonSelector(response, "datasets[" + x + "].schema.tables.name").size());
				for(int z = 0; z < setupStringListFromResponseAndJsonSelector(response, "datasets[" + x + "].schema.tables.name").size(); z++) {
					System.out.println(setupStringListFromResponseAndJsonSelector(response, "datasets[" + x + "].schema.tables[" +  z + "].name"));
				}
				//System.out.println(setupStringListFromResponseAndJsonSelector(response, "datasets[" + x + "].schema.tables.columns.name"));				
			}
			
			
			//for(int x = 0; x < listOfDataSetsNames.size(); x++) {
			//	listOfDataSetObjects.add(new DataSet(listOfDataSetsNames.get(x), listOfDataSetsOids.get(x)));
			//}
			
			//for(DataSet dset : listOfDataSetObjects) {
			//	dset.Show();
			//}


			//System.out.println(setupStringListFromResponseAndJsonSelector(response, "datasets.oid"));
			//System.out.println(setupStringListFromResponseAndJsonSelector(response, "datasets[0].schema.tables.oid"));
			
			
			
			
			//List<String> listOfTableNames = setupStringListFromResponseAndJsonSelector(response, "datasets.schema.tables.name");
			//System.out.println(listOfTableNames.size());
			
		}
		
		
		
		
		
		
}
