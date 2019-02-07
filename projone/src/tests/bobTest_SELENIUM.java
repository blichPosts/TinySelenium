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

public class bobTest_SELENIUM extends BaseSelenium
{
  
	
	@BeforeClass
	public static void setUp()    throws Exception
	{
		SetupDiver();
		CallGoogle();
		Thread.sleep(3000);
	}
	
	
	@Test
	public void FooBar() throws InterruptedException 
	{
		//PageFactory.
        GoogleSearchPage page = PageFactory.initElements(driver, GoogleSearchPage.class);
        page.searchFor("lichtenfels.org");
        Thread.sleep(2000);
        page.ClickGoogleSearch();
	}
	
	
	@AfterClass
	public void Finish() 
	{
		JOptionPane.showConfirmDialog(null, "MM");
		driver.close();
	}
	
	
	
	
}
