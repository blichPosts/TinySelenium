package tests;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DatePickerCruise extends BaseSelenium 
{

	@BeforeClass
	public static void setUp()    throws Exception
	{
		SetupDiver();
		SetupConfig();
		OpenUrlThree();
		Thread.sleep(3000);
		WaitForElementClickable(By.xpath("//button[contains(text(),'Search')]"), 5, "");

		////input[@placeholder='Check in']
	}
	
	
	@Test
	public void FooBarCruise() throws Exception 
	{
		driver.findElement(By.xpath("//input[@placeholder='Check in']")).click();	
		
		
		
		//tbody/tr/td[text()='1']

	}
	
	
	@AfterClass
	public void Finish() 
	{
		JOptionPane.showConfirmDialog(null, "MM");
		driver.close();
		driver.quit();
	}
	
	
	
}
