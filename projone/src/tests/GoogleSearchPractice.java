package tests;

import javax.swing.JOptionPane;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import actions.GoogleSearch;

public class GoogleSearchPractice extends BaseSelenium 
{

	@BeforeClass
	public static void setUp()    throws Exception
	{
		SetupDiver();
		SetupConfig();
	}
	
	@Test
	public void FooBar() throws Exception 
	{

		GoogleSearch.GetURL();
		GoogleSearch.ClickToools();
		
		//.//*[@id='gb192']/span[1]
		
		ShowText("Test Done");
	}
	
	@AfterClass
	public void Finish() 
	{
		JOptionPane.showConfirmDialog(null, "MM");
		driver.close();
		driver.quit();
	}	
}
