package tests;


import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import actions.RestAssuredActions;

public class RestTest {
	
	@BeforeClass
	public static void setUp()    throws Exception
	{
	}
	
	
	@Test
	public void RestAssured() throws Exception 
	{
	
		//RestAssuredActions.SanityCheck();
		//RestAssuredActions.CollectionWork();
		//RestAssuredActions.PostToken();
		//RestAssuredActions.Mobproc();
		//RestAssuredActions.MobprocTwo(); // this has list for headers.
		//RestAssuredActions.MobprocAssetsDevices();
		//RestAssuredActions.PostToken();
		//RestAssuredActions.postTokenSisense();
		RestAssuredActions.getSisenseUsers();
		RestAssuredActions.filterOnUser();
	}
	
	
	@AfterClass
	public void Finish() 
	{
	}
	
	
	

}
