package tests;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BaseSelenium 
{

	public static WebDriver driver;
	public static Properties config;
	public static FileInputStream fis;
	public static JFrame frame;	


	public static void SetupConfig() throws IOException
	{
		System.out.println(System.getProperty("user.dir") + "\\src\\Config.properties");
		config = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\Config.properties");
		config.load(fis);
		
		System.out.println(config.getProperty("UrlTrainOne"));
	}
	
	public static void SetupDiver()
	{
		String projectPath = ""; 
		File currentDirectory = new File(".");
		projectPath = currentDirectory.getAbsolutePath();
		System.out.println(projectPath);
		System.setProperty("webdriver.chrome.driver", projectPath + "\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("disable-infobars");  // <-- Line added by Ana. Needed because with the chromedriver 2.28, there's an info bar that we don't want to have when browser is launched
		driver = new ChromeDriver(options);			
	}
	
	public static void OpenUrlOne() // BAD
	{
        driver.navigate().to(config.getProperty("UrlTrainOne"));
        driver.manage().window().maximize();
	}
	
	public static void OpenUrlTwo()
	{
        driver.navigate().to(config.getProperty("UrlTrainTwo"));
        driver.manage().window().maximize();
	}
	
	public static void OpenUrlThree()
	{
        driver.navigate().to(config.getProperty("UrlTrainCruise"));
        driver.manage().window().maximize();
	}
	
	public static void OpenUrlScenarios()
	{
        driver.navigate().to(config.getProperty("UrlScenario"));
        driver.manage().window().maximize();
	}
	
	
	public static void CallGoogle()
	{
        // driver.get("http://www.google.com/");
        driver.navigate().to("http://www.google.com/");
        driver.manage().window().maximize();
	}
	
	public static void ShowPopup(String message)		
	{
		JOptionPane.showMessageDialog(frame, message);
	}

	public static void ShowText(String str) 
	{
		System.out.println(str);
	}
	
	public static void ShowWebElementListText(List<WebElement> eleList)
	{
		for(WebElement ele : eleList)
		{
			ShowText(ele.getText());
		}
	}
	
	public static void WebElementListTextRemoveEmptyStrings(List<WebElement> eleList)
	{
		for (Iterator<WebElement> iter = eleList.listIterator(); iter.hasNext(); ) 
		{
		    WebElement ele = iter.next();
		    if (ele.getText().equals("")) 
		    {
		        iter.remove();
		    }
		}	
	}

	
	public static boolean WaitForElementClickableBooleanNoThrow(By by, int waitTime)
	{
	    try
	    {
	    	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	    	wait.until(ExpectedConditions.elementToBeClickable(by));
	    }
	    catch (WebDriverException e)
	    {
	    	return false;
	    }
	    return true;
	}	

	public static void WaitForElementClickable(By by, int waitTime, String message)
	{
	    try
	    {
	    	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	    	wait.until(ExpectedConditions.elementToBeClickable(by));
	    }
	    catch (WebDriverException e)
	    {
	        System.out.println("Error in WaitForElementClickable: " + e.getMessage());
	    	throw new WebDriverException(message);
	    }
	}	
	
	// BAD !!!!!!!!!!!! ***************************************************
	// wait for an element to be not click-able. if an element is not click-able return true else false.  
	public static boolean WaitForElementNotClickableBoolean(By by, int waitTime)
	{
	    try
	    {
	    	WebDriverWait wait = new WebDriverWait(driver, waitTime);
	    	wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by))); // doesn't work.
	    	// wait.until(ExpectedConditions.not.elementToBeClickable(by)); // error in compile
	    	// https://stackoverflow.com/questions/42840725/selenium-expectedconditions-not-elementtobeclickable?rq=1
	    	//WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(By.id("")));
	    }
	    catch (WebDriverException e)
	    {
	    	return false;
	    }
	    return true;
	}	

	
	public static boolean WaitTestForElementNotClickable(By by, int waitTime)
	{
		long currentTime= System.currentTimeMillis();
		long endTime = currentTime + (waitTime * 1000);
		boolean elementNotClickable = false;
		
		while(System.currentTimeMillis() < endTime) 
		{
			if(!WaitForElementClickableBooleanNoThrow(by, 1)) // wait 1 second for response on each loop
			{
				elementNotClickable = true;
				break;
			}
		}
		return elementNotClickable;
	}
	
	
	public static boolean WaitTestForElementNotVisible(By by, int waitTime) throws Exception
	{
		long currentTime= System.currentTimeMillis();
		long endTime = currentTime + (waitTime * 1000);
		boolean elementNotVisible = false;
		
		while(System.currentTimeMillis() < endTime) 
		{
			if(!WaitForElementVisibleNoThrowBoolean(by, 1)) // wait 1 second for response on each loop
			{
				elementNotVisible = true;
				break;
			}
		}
		return elementNotVisible;
	}
	
	

	public static boolean WaitForElementVisible(By by, int timeOut) throws Exception 
	{
	    try
	    {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	    }
        catch (Exception e)
        {
	        //System.out.println(e.toString());
	        throw new Exception(e.toString());
        }	    
	    return true;
	}	
	
	public static boolean WaitForElementVisibleNoThrowBoolean(By by, int timeOut) throws Exception 
	{
	    try
	    {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	    }
        catch (Exception e)
        {
        	return false;
        }	    
	    return true;
	}	
	
	
	
}
