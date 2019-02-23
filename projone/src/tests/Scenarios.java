package tests;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import actions.Cruise;

public class Scenarios extends BaseSelenium
{

	@BeforeClass
	public static void setUp() throws Exception 
	{
		SetupDiver();
		SetupConfig();
		OpenUrlScenarios();
		Thread.sleep(3000);
		////input[@placeholder='Check in']
	}
	
	
	@Test
	public void FooBarEcenario() throws Exception 
	{
		actions.Scenarios.RunTestWaitForMissingElementsFastMethods();
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
