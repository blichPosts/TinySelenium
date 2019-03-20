package actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;

import tests.BaseSelenium;

public class DashBoardWithSideSelections extends BaseSelenium 
{
	public static String loginText = "opensourcecms"; 
	public static Actions actionGlobal = new Actions(driver);
	public static WebElement elementGlobal;
	
	public static void GetURL() throws InterruptedException
	{
		driver.navigate().to("https://s1.demo.opensourcecms.com/wordpress/wp-login.php"); 
		driver.manage().window().maximize();
		Thread.sleep(1000);
	}
	
	public static void Login()
	{
		WaitForElementClickable(By.xpath("//input[@name='log']"), 7);
		driver.findElement(By.xpath("//input[@name='log']")).sendKeys(loginText);
		driver.findElement(By.xpath("//input[@id='user_pass']")).sendKeys(loginText);
		driver.findElement(By.xpath("//input[@id='wp-submit']")).click();
	}
	
	public static void SelectThemes() throws Exception
	{
		Actions action = new Actions(driver);
		SetupSide();
		/*
		//WaitForElementClickable(By.xpath("//div[contains(text(),'Appearance')]"), 5);
		if(WaitTestForElementNotClickable(By.xpath("//div[contains(text(),'Appearance')]"), 5))
		{
			ClickLeftPaneOpen();
			WaitForElementClickable(By.xpath("//div[contains(text(),'Appearance')]"), 5);
		}
		*/
		WaitForElementClickable(By.xpath("//div[contains(text(),'Appearance')]"), 5);
		WebElement ele = driver.findElement(By.xpath("//div[contains(text(),'Appearance')]"));
		action.moveToElement(ele).perform();
		WaitForElementClickable(By.xpath("//a[text()='Themes']"), 5);
		driver.findElement(By.xpath("//a[text()='Themes']")).click();
		WaitForElementVisible(By.xpath("//h1[contains(text(),'Themes')]"), 5); // verify themes page
		//driver.navigate().back();
		GoHome();
		
		WaitForElementClickable(By.xpath("//a[@class='button button-primary button-hero load-customize hide-if-no-customize']"), 10);
		WaitForElementClickable(By.xpath("//span[@id='footer-thankyou']//a[contains(text(),'WordPress')]"), 10);		
	}
	
	public static void SideStuff() throws InterruptedException
	{
		SetupSide();
		//List<WebElement> elementList = driver.findElements(By.cssSelector("#adminmenuwrap>ul>li>ul>li"));
		List<WebElement> elementList = driver.findElements(By.cssSelector("#adminmenuwrap>ul>li"));
		
		for(WebElement element : elementList)
		{
			System.out.println(element.getText());
			if(element.getAttribute("class").contains("wp-has-submenu wp-not-current-submenu menu-top menu-icon-generic toplevel"))
			{
				//System.out.println("found");
				//System.out.println(element.getText());
				Actions action = new Actions(driver);
				action.moveToElement(element).perform();
				Thread.sleep(3000);
			}
		}

		
	}
	
	public static void SetupSide() throws InterruptedException
	{
		Actions action = new Actions(driver); 
		//WaitForElementClickable(By.xpath("//div[contains(text(),'Appearance')]"), 5);
		if(WaitTestForElementNotClickable(By.xpath("//div[contains(text(),'Appearance')]"), 5))
		{
			ClickLeftPaneOpen();
			WaitForElementClickable(By.xpath("//div[contains(text(),'Appearance')]"), 5);
		}
	}
	
	public static void WorkWithLeftPane() throws InterruptedException
	{

		ClickLeftPaneClose();
		
		Thread.sleep(1000);
		
		WaitForElementClickable(By.xpath("//div[@class='wp-menu-image dashicons-before dashicons-admin-settings']"), 3);
		elementGlobal =	driver.findElement(By.xpath("//div[@class='wp-menu-image dashicons-before dashicons-admin-settings']"));
		
		actionGlobal.moveToElement(elementGlobal).perform();
		
		WaitForElementClickable(By.xpath("//a[text()='General']"), 5);
		driver.findElement(By.xpath("//a[text()='General']")).click(); 
		
		ClickLeftPaneOpen();
		
	}
	
	public static void SelectNewOrder() throws Exception
	{
		Actions action = new Actions(driver);
		WaitForElementClickable(By.xpath("//span[@class='ab-label'][contains(text(),'New')]"), 5);
		//driver.findElement(By.xpath("//span[@class='ab-label']")).click();
		WebElement ele = driver.findElement(By.xpath("//span[@class='ab-label'][contains(text(),'New')]"));
		action.moveToElement(ele).perform();
		
	}
	
	public static void LogOut() throws Exception
	{
		WaitForElementClickable(By.xpath("//img[@class='avatar avatar-26 photo']"), 5);
		Actions action = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@class='avatar avatar-26 photo']"));
		action.moveToElement(ele).perform();
		WaitForElementClickable(By.xpath("//a[text()='Log Out']"), 30);
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		WaitForElementClickable(By.xpath("//input[@name='log']"), 7); // wait for logout page
	}
	
	public static void GoHome()
	{
		WaitForElementClickable(By.xpath("//div[contains(text(),'Dashboard')]"), 5);
		driver.findElement(By.xpath("//div[contains(text(),'Dashboard')]")).click();		
	}
	
	public static void ClickLeftPaneClose()
	{
		WaitForElementClickable(By.xpath("//span[@class='collapse-button-label']"), 5); 
		driver.findElement(By.xpath("//span[@class='collapse-button-label']")).click(); // hide left pane
		
	}
	
	public static void ClickLeftPaneOpen() throws InterruptedException
	{
		WaitForElementClickable(By.xpath(".//*[@id='collapse-button']"), 5); 
		driver.findElement(By.xpath(".//*[@id='collapse-button']")).click();
		WaitForElementClickable(By.xpath("//span[@class='collapse-button-label']"), 5);
	}
}
