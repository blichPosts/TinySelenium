package actions;

import org.openqa.selenium.By;

import tests.BaseSelenium;

public class GoogleSearch extends BaseSelenium 
{
	public static void GetURL() throws InterruptedException
	{
		driver.navigate().to("https://www.google.com/"); 
		driver.manage().window().maximize();
		Thread.sleep(1000);
	}
	
	public static void ClickMike()
	{
		WaitForElementPresent(By.xpath("//a[@title='Google apps']"), 5);
		driver.findElement(By.xpath("//span[@class='hb2Smf']")).click();
		
	}
	
	public static void ClickToools()
	{
		WaitForElementPresent(By.xpath("//a[@title='Google apps']"), 5);
		driver.findElement(By.xpath("//a[@title='Google apps']")).click();
		WaitForElementClickable(By.xpath("//a[@id='gb36']//span[@class='gb_k']"), 3);
		driver.findElement(By.xpath("//a[@id='gb36']//span[@class='gb_k']")).click();
	}
}
