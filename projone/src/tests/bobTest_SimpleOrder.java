package tests;
import java.io.File;

import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import actions.Orders;

public class bobTest_SimpleOrder extends BaseSelenium
{
  
	
	@BeforeClass
	public static void setUp()    throws Exception
	{
		SetupDiver();
		SetupConfig();
		OpenUrlTwo();
		Thread.sleep(3000);
	}
	
	
	@Test
	public void FooBar() throws Exception 
	{
		

		Orders.WaitForMainPageToLoad();
		Orders.SetupTestLists();
		//Orders.PassOne();
		
		// remove all items added to the cart
		//Orders.PassTwo();
		Orders.PassThree(); 
		Orders.PassFour(); 
		
		
		ShowText("Test Done.");
		
		// look at items in cart pulldown.
		////div[@class='product-name']
		//div/a[@title='View my shopping cart']
		
		//Orders.GoToOrderPage();
		//Orders.GetPrices();
	}
	
	
	@AfterClass
	public void Finish() 
	{
		JOptionPane.showConfirmDialog(null, "MM");
		driver.close();
		driver.quit();
	}
	
	
	
	
}
