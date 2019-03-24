package tests;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import actions.Cruise;

public class Charts extends BaseSelenium 
{


	@BeforeClass
	public static void setUp()    throws Exception
	{
		SetupDiver();
		SetupConfig();
		DashURL();
	}
	
	
	@Test
	public void Dash() throws Exception 
	{
	
		//         driver.navigate().to(config.getProperty("UrlTrainCruise"));
        //driver.manage().window().maximize();
		//a[@id='CybotCookiebotDialogBodyLevelButtonAccept']
		WaitForElementClickable(By.xpath("//a[@id='CybotCookiebotDialogBodyLevelButtonAccept']"), 3);
		driver.findElement(By.xpath("//a[@id='CybotCookiebotDialogBodyLevelButtonAccept']")).click();
		
		WaitForElementClickable(By.cssSelector(".raphael-group-53-vcanvas-column-plot>g:nth-of-type(3)>rect:nth-of-type(1)"),3);
		driver.findElement(By.cssSelector(".raphael-group-53-vcanvas-column-plot>g:nth-of-type(3)>rect:nth-of-type(1)")).click();
		
		//.raphael-group-53-vcanvas-column-plot>g:nth-of-type(3)>rect:nth-of-type(1)


	}
	
	
	@AfterClass
	public void Finish() 
	{
		JOptionPane.showConfirmDialog(null, "MM");
		driver.close();
		driver.quit();
	}
	
	
	
}
