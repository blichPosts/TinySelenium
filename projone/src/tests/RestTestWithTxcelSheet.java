package tests;


import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import actions.ExcelSheetActions;
import actions.RestAssuredActions;

public class RestTestWithTxcelSheet {
	
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
		//RestAssuredActions.postTokenSisense(); // admin user
		//RestAssuredActions.getSisenseUsers();
		//RestAssuredActions.filterOnUser();
		//RestAssuredActions.getFoldersTwo();
		//RestAssuredActions.getFoldersThree();
		//RestAssuredActions.getFoldersFourBuildList();
		
		

		//RestAssuredActions.postTokenSisenseForSpecifiedUser("bob.lichtenfels@tangoe.com");
		//RestAssuredActions.getSpecificUserSisenseDashboardDataAdmin();
		//RestAssuredActions.getSpecificUserSisenseDashboardDataAdmin();
		// RestAssuredActions.WorkOnElasticCubes();
		//RestAssuredActions.logoutSisenseToken();

		
		ExcelSheetActions.readExcelSheet();
		
		
	}
	
	
	@AfterClass
	public void Finish() 
	{
	}
	
	
	

}
