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
	
		// RestAssuredActions.SanityCheck();
		RestAssuredActions.CollectionWork();
		

	}
	
	
	@AfterClass
	public void Finish() 
	{
	}
	
	
	

}
