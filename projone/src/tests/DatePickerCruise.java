package tests;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import actions.Cruise;

public class DatePickerCruise extends BaseSelenium 
{

	@BeforeClass
	public static void setUp()    throws Exception
	{
		/*
		SetupDiver();
		SetupConfig();
		OpenUrlThree();
		Thread.sleep(3000);
		*/
		////input[@placeholder='Check in']
	}
	
	
	@Test
	public void FooBarCruise() throws Exception 
	{
	
		// Cruise.GetCalendartest();
		//Cruise.SetupStartEndDates();
		//Cruise.MakeDateSelections();
		Cruise.DatePractice();
		
		


	}
	
	
	@AfterClass
	public void Finish() 
	{
		JOptionPane.showConfirmDialog(null, "MM");
		//driver.close();
		//driver.quit();
	}
	
	
	
}
