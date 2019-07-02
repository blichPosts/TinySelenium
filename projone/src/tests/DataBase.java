package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import actions.DataBaseActions;
import actions.RestAssuredActions;

public class DataBase {
	
	@BeforeClass
	public static void setUp()    throws Exception
	{
	}
	
	
	@Test
	public void DataBaseTest() throws Exception 
	{
		DataBaseActions.Connect();

	}
	
	
	@AfterClass
	public void Finish() 
	{
	}
}
