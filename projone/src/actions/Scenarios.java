package actions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import tests.BaseSelenium;


// About actions
// https://stackoverflow.com/questions/49459040/why-do-we-need-robot-class-when-we-have-actions-class-in-selenium

public class Scenarios extends BaseSelenium 
{

	public static void RunTestWaitForMissingElementsFastMethods() throws Exception
	{
		WaitForElementClickable(By.xpath("//a[contains(text(),'Dynamic Controls')]"), 7, "");
		driver.findElement(By.xpath("//a[contains(text(),'Dynamic Controls')]")).click();
		
		//a[contains(text(),'Dynamic Controls')]
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
	
	public static void GoBackToMainPage()
	{
        driver.navigate().to(config.getProperty("UrlScenarioMain")); 
	}
	
	// this works.
	public static void DragAndDropExample() throws Exception
	{
	    driver.get("http://demo.guru99.com/test/drag_drop.html");

	    WaitForElementVisible(By.xpath("(//h3[contains(text(),'Account' )] )[2]"), 5);
	    //Thread.sleep(5000);
	    ShowText("do");		
	    
		//Element which needs to drag.    		
    	WebElement From = driver.findElement(By.xpath("//*[@id='credit2']/a"));	
     
	     //Element on which need to drop.		
	     WebElement To = driver.findElement(By.xpath("//*[@id='bank']/li"));					
	     		
	     //Using Action class for drag and drop.		
	     Actions act = new Actions(driver);					
	
	     //Dragged and dropped.		
	     act.dragAndDrop(From, To).build().perform();		

	     //Element which needs to drag.    		
	     From = driver.findElement(By.xpath("//a[contains(text(),'SALES')]"));	
	     
	     //Element on which need to drop.		
	     To = driver.findElement(By.xpath("//ol[@id='loan']"));					

	     ShowText("Pause for 2 seconds");
	     Thread.sleep(2000);

	     //Using Action class for drag and drop. // need to recreate act object		 
	     //act = new Actions(driver);					

	     //Dragged and dropped.		
	     act.dragAndDrop(From, To).build().perform();		

	     
		
	}
	
	
	
	public static void DragAndDrop() throws InterruptedException
	{
		boolean runOld = false;
		
		//WaitForElementClickable(By.xpath("//a[contains(text(),'Drag and Drop')]"), 7, "");
		//driver.findElement(By.xpath("//a[contains(text(),'Drag and Drop')]")).click();
		//WaitForElementClickable(By.xpath("//div[@id='column-a']"), 7, "");
		//WaitForElementClickable(By.xpath("//div[@id='column-b']"), 7, "");
		
		driver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");	
		
			
		Thread.sleep(2000);
		
		// look at this
		//https://sqa.stackexchange.com/questions/34477/how-to-drag-and-drop-two-elements-in-the-same-table-selenium-java
		
		if(runOld)
		{
			//WebElement from = driver.findElement(By.xpath("//div/header[text()='A']"));
			//WebElement to = driver.findElement(By.xpath("//div/header[text()='B']"));
			
			WebElement from = driver.findElement(By.xpath("//div[@id='column-a']"));
			WebElement to = driver.findElement(By.xpath("//div[@id='column-b']"));
			
			//WebElement from = driver.findElement(By.xpath("//*[@id='column-a']"));
			//WebElement to = driver.findElement(By.xpath("//*[@id='column-b']"));

			
			ShowText("Start");
			
	        //Using Action class for drag and drop.		
	        Actions act = new Actions(driver);	

	        //act.moveToElement(from).perform();;
	        
	    	//Dragged and dropped.		
	        //act.dragAndDrop(from, to).build().perform();
	        act.dragAndDrop(to, from).build().perform();
		}
		else
		{
			WebElement from = driver.findElement(By.xpath("//div[@id='column-a']"));
			WebElement to = driver.findElement(By.xpath("//div[@id='column-b']"));

			ShowText("Start click and hold method");
			
	        //Using Action class for drag and drop.		
	        Actions act = new Actions(driver);	

	        //act.moveToElement(from).perform();;
	        
	    	//Dragged and dropped.		
	        Actions action = new Actions(driver);
			action.moveToElement(from).perform();
	        
	        // nope
	        //action.clickAndHold(from).perform();                      
			// moveToElement(to).release(to).perform();			
		}
	}
	
	public static void DragAndDropCursorMove() throws InterruptedException, AWTException
	{
		driver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");	
			
		Thread.sleep(2000);
		
		
		//Point coordinates = driver.findElement(By.xpath("//div[@id='column-a']")).getLocation();
		//Point coordinates = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[1]")).getLocation();
		//Robot robot = new Robot();
		//robot.mouseMove(coordinates.getX(),coordinates.getY());
		
		// look at this
		//https://sqa.stackexchange.com/questions/34477/how-to-drag-and-drop-two-elements-in-the-same-table-selenium-java
		
		//WebElement from = driver.findElement(By.xpath("//div[@id='column-a']"));
		//WebElement to = driver.findElement(By.xpath("//div[@id='column-b']"));

		//WebElement from = driver.findElement(By.xpath("//div/header[text()='A']"));
		//WebElement to = driver.findElement(By.xpath("//div/header[text()='B']"));
		 
		 
		//WebElement from = driver.findElement(By.xpath("//*[@id='column-a']"));
		//WebElement to = driver.findElement(By.xpath("//*[@id='column-b']"));
		 
		WebElement from = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[1]"));
		WebElement to = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]"));
		 
        Actions act = new Actions(driver);		
		
	     //Dragged and dropped.		
	     act.dragAndDrop(from, to).build().perform();		
	}
	
	public static void JqueryMenu() throws Exception
	{
		driver.navigate().to("http://the-internet.herokuapp.com/jqueryui/menu");
		WaitForElementClickable(By.xpath("//div/a[contains(text(),'Elemental Selenium')]"), 7, "");
		WaitForElementVisible(By.xpath("//a[text()='JQuery UI Menus']"), 7);
		driver.findElement(By.xpath(".//*[@id='ui-id-2']")).click();
		driver.findElement(By.xpath("//a[text()='Back to JQuery UI']")).click();

		WaitForElementClickable(By.xpath("//a[contains(text(),'JQuery UI')]"), 7, "");
		driver.findElement(By.xpath("//a[contains(text(),'JQuery UI')]")).click();

		driver.navigate().back();
		
		WaitForElementClickable(By.xpath("//a[contains(text(),'Menu')]"), 7, "");
		driver.findElement(By.xpath("//a[contains(text(),'Menu')]")).click();
		
		WaitForElementClickable(By.xpath("//a[text()='Enabled']"), 7, "");
		driver.findElement(By.xpath("//a[text()='Enabled']")).click();
		driver.findElement(By.xpath("//a[text()='Downloads']")).click();
		Thread.sleep(1000);
		//ShowPopup("---");
		// driver.findElement(By.xpath("//a[text()='PDF']")).click(); // conflict with powered by text.	
		
		//a[contains(text(),'JQuery UI')]
		
	}
	
	public static void FileUpLoadGuruExample() throws InterruptedException
	{
		driver.navigate().to("http://demo.guru99.com/test/upload/");
		//ShowText("Wait 5 seconds");
		//Thread.sleep(5000);
		
		WaitForElementClickable(By.id("uploadfile_0"), 7, "");
        WebElement uploadElement = driver.findElement(By.id("uploadfile_0")); // ---------------> this is the choose file button

        // enter the file path onto the file-selection input field
        uploadElement.sendKeys("C:\\SoapUi_Projects\\Json.txt"); // file must exist

        // check the "I accept the terms of service" check box
        driver.findElement(By.id("terms")).click();

        // click the "UploadFile" button ------------------->> this is the button called 'Submit File'
        driver.findElement(By.name("send")).click();
	}

	public static void FileUpLoadSeleiumExample() throws InterruptedException 
	{
		driver.navigate().to("http://the-internet.herokuapp.com/upload");
		
		WaitForElementClickable(By.xpath("//input[@id='file-upload']"), 7, "");
		WebElement uploadElement = driver.findElement(By.xpath("//input[@id='file-upload']")); // ---------------> this is the choose file button
		//WebElement uploadElementDropFile = driver.findElement(By.xpath("//div[@id='drag-drop-upload']")); // ---------------> this is the drag/drop box. (NOPE) 
		
		
        // enter the file path onto the file-selection input field
        
		uploadElement.sendKeys("C:\\SoapUi_Projects\\Json.txt"); // file must exist 
		//uploadElementDropFile.sendKeys("C:\\SoapUi_Projects\\Json.txt"); // file must exist (NOPE)
		
		
        WaitForElementClickable(By.xpath("//input[@id='file-submit']"), 7, "");
        driver.findElement(By.xpath("//input[@id='file-submit']")).click();
	}
	
	// https://www.guru99.com/alert-popup-handling-selenium.html
	public static void BasicPopupAlert() throws InterruptedException
	{
		driver.navigate().to("http://demo.guru99.com/test/delete_customer.php");
		WaitForElementClickable(By.xpath("//input[@value='Submit']"), 7);
		WaitForElementClickable(By.xpath("//input[@name='cusid']"), 7);		
		
		
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys("ABC");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
        // Switching to Alert        
        Alert alert = driver.switchTo().alert();		
        		
        // Capturing alert message.    
        ShowText("Show Message pop-up and wait - " + driver.switchTo().alert().getText());
        
       Thread.sleep(5000); 
        
        // Accepting alert		
        alert.accept();	        
		
        // Capturing alert message.    
        ShowText("Show Message pop-up and wait - " + driver.switchTo().alert().getText());
		
        Thread.sleep(5000);

        // Accepting alert		
        alert.accept();        
	}
	
	// https://www.guru99.com/alert-popup-handling-selenium.html
	public static void MultipleWindowsPopUp() throws Exception
	{
		boolean foundWindow = false;
		
		driver.navigate().to("http://demo.guru99.com/popup.php");
		WaitForElementClickable(By.xpath("//a[contains(text(),'Click Here')]"), 7);
		Thread.sleep(1000);
		
		// get the current window handle
		String mainWindow = driver.getWindowHandle();
				
		ShowText("Windows at start");
		ShowWindowsHandles();
		
		// now click to go to another window.
		driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click(); // this will open new window in browser
		
		ShowText("Wait for new window");
		Thread.sleep(3000);
		
		ShowText("Windows after app creates new window.");
		ShowWindowsHandles();
		
		// now have to find the new window, which should not be the 'mainWindow', and switch to it.
		// then wait for the click-able element
		Set<String> setOfWindowHandles = driver.getWindowHandles();
		for(String str : setOfWindowHandles)
		{
			if(!str.equalsIgnoreCase(mainWindow))
			{
				driver.switchTo().window(str);
				WaitForElementClickable(By.xpath("//input[@value='Submit']"), 5);
				foundWindow = true;
				break;
			}
		}

		if(!foundWindow)
		{
			Assert.fail("Failed to find window.");
		}
		
		// enter mail and click.
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("lich@comemail.com");		
		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		// verify items in same window
		WaitForElementVisible(By.xpath("//td[contains(text(),'User ID :')]"), 7);
		WaitForElementClickable(By.xpath("//a[contains(text(),'Click Here')]"), 7);		
		driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();
	}

	public static void MultipleWindowaPopOut() throws InterruptedException
	{
		boolean foundWindow = false;
		Set<String> namesOfPopupWindows = new HashSet<String>(); 
		
		driver.navigate().to("http://book.theautomatedtester.co.uk/chapter1");
		WaitForElementClickable(By.xpath("//body//div[5]"), 7);
		
		String mainWindow = driver.getWindowHandle();
		
		ShowText("Windows at start");
		ShowWindowsHandles();

		ShowText("Click new window");
		driver.findElement(By.xpath("//body//div[5]")).click();
		Thread.sleep(1000);

		// //body//div[6]
		
		
		
		
		ShowText("Wait for new window");
		Thread.sleep(3000);
		
		ShowText("Windows after app creates new window.");
		ShowWindowsHandles();
		
		// now have to find the new window, which should not be the 'mainWindow', and switch to it.
		// then wait for the click-able element
		Set<String> setOfWindowHandles = driver.getWindowHandles();
		for(String str : setOfWindowHandles)
		{
			if(!str.equalsIgnoreCase(mainWindow))
			{
				driver.switchTo().window(str);
				WaitForElementClickable(By.xpath("//p[@id='closepopup']"), 5);
				foundWindow = true;
				break;
			}
		}

		if(!foundWindow)
		{
			Assert.fail("Failed to find window.");
		}

		WaitForElementClickable(By.xpath("//p[@id='closepopup']"), 7);
		
		
	}
	
	
	public static void Hovers() throws InterruptedException
	{
		
		driver.navigate().to("http://the-internet.herokuapp.com/hovers");
		// http://the-internet.herokuapp.com/hovers
		
		WaitForElementClickable(By.xpath("//div[@class='figure']/img"), 7);
		WebElement element = driver.findElement(By.xpath("//div[@class='figure']/img"));
		
		String mainWindow = driver.getWindowHandle(); //. store current frame
		ShowWindowsHandles();
		
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		
		WaitForElementClickable(By.xpath("//a[text()='View profile']"), 5);
		driver.findElement(By.xpath("//a[text()='View profile']")).click();
		Thread.sleep(2000);
		
		ShowWindowsHandles();
	}

	public static void NestedFrames() throws Exception 
	{
		driver.navigate().to("http://the-internet.herokuapp.com/frames");
		WaitForElementClickable(By.xpath("//a[contains(text(),'Nested Frames')]"), 7);
		driver.findElement(By.xpath("//a[contains(text(),'Nested Frames')]")).click();
		Thread.sleep(2000);

		ShowText("Go to parent window and show frames");
		
		driver.switchTo().parentFrame();		
		Thread.sleep(2000);

		List<WebElement> iframes = driver.findElements(By.tagName("FRAME"));
		System.out.println(iframes.size());

		// show frames as seen from parent frame
		for(WebElement ele : iframes){ShowText("FrameName:" + ele.getAttribute("name"));}
		
		ShowText("Go to top frame at parent -> frame-top and show frames in that.");
		
		// now go to top frame under parent frame
		driver.switchTo().frame("frame-top");
		Thread.sleep(2000);		
		
		// show frames as seen from frame top.
		iframes = driver.findElements(By.tagName("FRAME"));
		for(WebElement ele : iframes){ShowText("FrameName:" + ele.getAttribute("name"));}

		driver.switchTo().frame("frame-left");
		Thread.sleep(1000);
		ShowText(driver.findElement(By.xpath("//body")).getText());

		// ONLY WAY TO GET TO MIDDEL TOP - HAD TO REFRESH PAGE - couldn't go up to parent and work down to top then middle. ??????????????????????????????
		driver.navigate().refresh();
		Thread.sleep(2000);		
		
		driver.switchTo().parentFrame();		
		Thread.sleep(2000);
		
		driver.switchTo().frame("frame-top");
		Thread.sleep(2000);		
		
		driver.switchTo().frame("frame-middle");
		Thread.sleep(1000);
		ShowText(driver.findElement(By.xpath("//body")).getText());

		// ONLY WAY TO GET TO MIDDEL TOP - HAD TO REFRESH PAGE - couldn't go up to parent and work down to top then middle. ??????????????????????????????
		driver.navigate().refresh();
		Thread.sleep(2000);		
		
		driver.switchTo().parentFrame();		
		Thread.sleep(2000);
		
		driver.switchTo().frame("frame-top");
		Thread.sleep(2000);		
		
		driver.switchTo().frame("frame-right");
		Thread.sleep(1000);
		ShowText(driver.findElement(By.xpath("//body")).getText());

		
		// //h3[contains(text(),'An iFrame containing the TinyMCE WYSIWYG Editor')]
		
		
	}
	
	// put in text and select an item in pull-down - frames are used. 
	public static void IFrame() throws Exception
	{
		// now go to iframe
		driver.navigate().to("http://the-internet.herokuapp.com/iframe");
		
		WaitForElementVisible(By.xpath("//h3[contains(text(),'An iFrame containing the TinyMCE WYSIWYG Editor')]"), 5);
		
		// got to inner frame to send text.
		driver.switchTo().frame("mce_0_ifr");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//html/body")).clear();
		driver.findElement(By.xpath("//html/body")).sendKeys("Test text in window...");
		
		// go to default frame to use pull-down.
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button/span[text()='Format']")).click(); // this shows pull-down list
		// new Select(driver.findElement(By.xpath("//div[@id='mceu_18']"))).selectByIndex(1); // problems in iFrame
		
		WaitForElementClickable(By.xpath("//div[@id='mceu_35']"), 3);
		driver.findElement(By.xpath("//div[@id='mceu_35']")).click();
	}
	
	
	// NOTE: can't send lower case - lower case is set to upper case when run this manually in tutorial 
	public static void KeyPress() throws Exception
	{
		// http://the-internet.herokuapp.com/key_presses
		driver.navigate().to("http://the-internet.herokuapp.com/key_presses");
		
		WaitForElementVisible(By.xpath("//h3[contains(text(),'Key Presses')]"), 7);
		
		Thread.sleep(3000);

		// this only works for modifier keys -- https://en.wikipedia.org/wiki/Modifier_key
	    //Actions act = new Actions(driver);
	    //act.keyDown(Keys.ALT).perform(); // send modifier
	    //Thread.sleep(3000);
		
		char myChar = 'a';
		
	    Robot r = new Robot();
	    /*
	    r.keyPress(KeyEvent.VK_N);
	    // r.keyPress(myChar); // nope 
	    
	    r.delay(5000); // delay in milliseconds.

	    r.keyRelease(KeyEvent.VK_N);
	    r.delay(5000); // delay in milliseconds.
	    
	    int keyCode = KeyEvent.getExtendedKeyCodeForChar('b');
	    
	    r.keyPress(keyCode);
	    r.delay(1000); // delay in milliseconds.
	    */

	    r.keyPress(KeyEvent.VK_SHIFT);
	    r.delay(1000); // delay in milliseconds.
	    
	    r.keyPress(KeyEvent.VK_B);
	    r.delay(1000); // delay in milliseconds.

	    r.keyRelease(KeyEvent.VK_SHIFT);
	    r.delay(5000); // delay in milliseconds.
	    
	    r.keyRelease(KeyEvent.VK_B);
	    r.delay(5000); // delay in milliseconds.
	}
	
	public static void JavaScriptAlerts() throws InterruptedException
	{
		
		driver.navigate().to("http://the-internet.herokuapp.com/javascript_alerts");
		
		WaitForElementClickable(By.xpath("//button[@onclick='jsAlert()']"), 5);
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click(); // click alert 1
		
		// verify text 
		Alert alert = driver.switchTo().alert();
		ShowText(alert.getText());
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		Thread.sleep(500);

		// verify accept
		alert.accept();
		Thread.sleep(500);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You successfuly clicked an alert");

		WaitForElementClickable(By.xpath("//button[@onclick='jsConfirm()']"), 5);
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click(); // click alert 2
		Thread.sleep(500);
		alert = driver.switchTo().alert();
		alert.dismiss();
		Thread.sleep(500);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
		
		// send info to pop-up -- NOTE!!! you don't visually see the info sent to the pop-up from selenium. 
		WaitForElementClickable(By.xpath("//button[@onclick='jsPrompt()']"), 5);
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click(); // click alert 3
		Thread.sleep(1500);
		alert = driver.switchTo().alert();
		ShowText(alert.getText());
		Thread.sleep(5000);
		String textToSend = "Foo Bar";
		alert.sendKeys(textToSend);
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + textToSend);
		
		
	}
	
	
	// //////////////////////////////////////////////////////////////////////////////////////
	// 									Helpers
	// //////////////////////////////////////////////////////////////////////////////////////
	
	public static void ShowWindowsHandles()
	{
		ShowText("Current Windows ------------------------------------------");
		Set<String> setOfWindowHandles = driver.getWindowHandles();
		
		for(String str : setOfWindowHandles)
		{
			ShowText(str);
		}
		
	}
	
	
}
