package tests;

import javax.swing.JOptionPane;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import actions.HttpRequestActions;

public class HttpRequest extends BaseSelenium 
{


	@BeforeClass
	public static void setUp()    throws Exception
	{
		//SetupDiver();
		//SetupConfig();
		////input[@placeholder='Check in']
	}
	
	
	@Test
	public void Httprequest() throws Exception 
	{
		HttpRequestActions.Connect();
		//HttpRequestActions.SetToken();

	}
	
	
	@AfterClass
	public void Finish() 
	{
		//JOptionPane.showConfirmDialog(null, "MM");
		//driver.close();
		//driver.quit();
	}
	
	
		
	
	
}
