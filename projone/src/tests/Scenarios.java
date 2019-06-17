package tests;


import javax.swing.JOptionPane;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Scenarios extends BaseSelenium
{

	@BeforeClass
	public static void setUp() throws Exception 
	{
		SetupDiver();
		SetupConfig();
		OpenUrlScenarios();
		Thread.sleep(3000);
	}
	
	
	@Test
	public void FooBarScenario() throws Exception 
	{
		//actions.Scenarios.RunTestWaitForMissingElementsFastMethods(); // good
		//actions.Scenarios.GoBackToMainPage();
		//actions.Scenarios.DragAndDrop(); // coudn't get to work - this goes to it's own web site also
		//actions.Scenarios.DragAndDropExample(); // works - this method goes to the URL for the example in the method	
		//actions.Scenarios.JqueryMenu();
		
		// try download files
		// http://the-internet.herokuapp.com/upload
		//actions.Scenarios.FileUpLoadGuruExample(); // good
		//actions.Scenarios.FileUpLoadSeleiumExample(); // here - how would you do drag and drop? // good
		
		// pop-up - message boxes - window handles - hovers
		//actions.Scenarios.BasicPopupAlert(); // good
		//actions.Scenarios.MultipleWindowsPopUp(); // good
		//actions.Scenarios.Hovers(); // good
		//actions.Scenarios.NestedFrames(); // good
		//actions.Scenarios.IFrame(); // good
		//actions.Scenarios.KeyPress(); // good 
		//actions.Scenarios.JavaScriptAlerts(); // good 
		// actions.Scenarios.MultipleWindowsPopOut(); // good - hacked
		// actions.Scenarios.HashMap(); // this is for weird user code - goView
		actions.Scenarios.AtlasGoView();
		
		ShowText("Test done");
	}
	
	@AfterClass
	public void Finish() 
	{
		JOptionPane.showConfirmDialog(null, "MM");
		driver.close();
		driver.quit();
	}
}
