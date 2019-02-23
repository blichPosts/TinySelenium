package actions;

import org.openqa.selenium.By;

import tests.BaseSelenium;

public class Scenarios extends BaseSelenium 
{

	public static void RunTestWaitForMissingElementsFastMethods() throws Exception
	{
		WaitForElementClickable(By.xpath("//input[@type='checkbox']"), 7, "");
		WaitForElementClickable(By.xpath("//button[contains(text(),'Remove')]"), 7, "");
		WaitForElementVisible(By.xpath("//button[contains(text(),'Remove')]"), 7);
		
		driver.findElement(By.xpath("//button[contains(text(),'Remove')]")).click();

		
		ShowText("start checkbox not clickable");		
		if(WaitTestForElementNotClickable(By.xpath("//input[@type='checkbox']"), 25))
		{
			ShowText("element not clickable");
		}
		
		// uncomment this and it should fail because the if statement above verified it not click-able.
		//WaitForElementClickable(By.xpath("//input[@type='checkbox']"), 7, "");
		
		ShowText("start remove text not clicable");
		if(WaitTestForElementNotVisible(By.xpath("//button[contains(text(),'Remove')]"), 25))
		{
			ShowText("element not visible");
		}
		
		// uncomment this and it should fail because the if statement above verified it not visible. 
		//WaitForElementVisible(By.xpath("//button[contains(text(),'Remove')]"), 7); 
		
		
		// Below method Bad -- see code
		//ShowText("start 2");
		//System.out.println(WaitForElementNotClickableBoolean(By.xpath("//input[@type='checkbox']"), 7));
		
	}
	
	
}
