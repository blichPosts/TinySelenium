package orders;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.w3c.dom.NameList;

import tests.BaseSelenium;

public class Orders extends BaseSelenium
{
	
	public static List<WebElement> eleList = new ArrayList<WebElement>(); 
	public static List<WebElement> namesList = new ArrayList<WebElement>();
	public static List<WebElement> costList = new ArrayList<WebElement>();
	public static List<WebElement> localWebList =  new ArrayList<WebElement>();
	
	public static void WaitForMainPageToLoad() throws Exception
	{
		WaitForElementClickable(By.xpath("//button[@name='submit_search']"), 10, "");
		WaitForElementClickable(By.xpath("//a[text()='Selenium Framework']"), 10, "");		
	}
	
	
	public static void SetupTestLists()
	{
		eleList = driver.findElements(By.cssSelector("#homefeatured>li>div>div:nth-of-type(1)"));	
		namesList = driver.findElements(By.cssSelector("#homefeatured>li>div>div:nth-of-type(2)>h5>a"));
		costList = driver.findElements(By.cssSelector("#homefeatured>li>div>div:nth-of-type(2)>div>span:nth-of-type(1)"));

		// remove empty strings from costList 
		WebElementListTextRemoveEmptyStrings(costList);
		// show lists
		//ShowWebElementListText(costList);
		//ShowWebElementListText(namesList);

	}
	
	// see link
	// http://executeautomation.com/blog/mouse-hover-click-selenium/
	public static void PassOne() throws Exception
	{
		int cntr = 1;
		String tempCost =  "";
		String tempItemName =  "";

		for(WebElement ele : eleList)
		{
			// get expected values from web element lists before opening new UI. web element lists go out of scope when in new UI.
			tempCost = costList.get(cntr - 1).getText();			
			tempItemName = namesList.get(cntr - 1).getText();		
			
			Actions action = new Actions(driver);
			action.moveToElement(ele).perform(); 
			
			// select 'quick view' hover select over current web element   
			WaitForElementClickable(By.xpath(".//*[@id='homefeatured']/li[" +  cntr +  "]/div/div[1]/div/a[2]/span"), 7, "");
			driver.findElement(By.xpath(".//*[@id='homefeatured']/li[" +  cntr +  "]/div/div[1]/div/a[2]/span")).click();
			Thread.sleep(3000); // have to do this wait because frame switch is done in window popping up
			
			// switch to correct frame and select 'add to cart',  after verifying item name and cost.
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='fancybox-iframe']")));
			WaitForElementVisible(By.xpath("//label[contains(text(),'Quantity')]"), 7);
			WaitForElementClickable(By.xpath("//span[contains(text(),'Add to cart')]"), 7, "");
			VerifyExpectedItems(tempCost, tempItemName); // verification
			
			driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click(); // select add to cart and do nothing
			
			Thread.sleep(3000); // below click sometimes gives 'Other element would receive the click:'. need wait.
			WaitForElementClickable(By.xpath("//div[@class='button-container']//span[1]//span[1]"), 7, "");
			driver.findElement(By.xpath("//div[@class='button-container']//span[1]//span[1]")).click(); // go back to main page

			// wait for main page.
			WaitForElementClickable(By.xpath("//a[@class='blockbestsellers']"), 5, "");
			
			Thread.sleep(500);
			cntr++;
		}
	}
	
	// add to shop cart and remove in shop cart.
	public static void PassTwo() throws Exception
	{

		OrderItemByIndexFromMainPage(1);
		ReturnToMainPageFromShoppingCart();
		OrderItemByIndexFromMainPage(2);
		ReturnToMainPageFromShoppingCart();
		OrderItemByIndexFromMainPage(3);
		ReturnToMainPageFromShoppingCart();
		OrderItemByIndexFromMainPage(4);
		
		ShowPopup("");

		System.out.println(WaitForElementClickableBooleanNoThrow(By.xpath("(//i[@class='icon-trash'])[1]"), 5));
		
		if(WaitForElementClickableBooleanNoThrow(By.xpath("(//i[@class='icon-trash'])[1]"), 5))
		{
			localWebList = driver.findElements(By.xpath("//i[@class='icon-trash']"));
			for(WebElement ele: localWebList)
			{
				ele.click();
				Thread.sleep(3000);
			}
		}
		
		
	}
	
	
	public static void PassThree() throws Exception
	{
		int itemIndex = 3;
		
		OrderItemByIndexFromMainPage(itemIndex);
		ReturnToMainPageFromShoppingCart();

		SetupTestLists();		
		
		WaitForElementClickable(By.xpath("//a[@title='View my shopping cart']"), 5, "");
		WebElement ele = driver.findElement(By.xpath("//a[@title='View my shopping cart']"));
		
		Thread.sleep(1000);
		
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform(); 
		ShowText("sleep");
		Thread.sleep(1000);
		WaitForElementClickable(By.xpath(".//*[@id='button_order_cart']/span"), 5, "");

		ShowText(driver.findElement(By.xpath("//span[@class='quantity']")).getText());
		ShowText(costList.get(itemIndex - 1).getText());
		ShowText(driver.findElement(By.xpath("//span[@class='price']")).getText());
		ShowText(namesList.get(itemIndex - 1).getText());
		ShowText(driver.findElement(By.xpath("//a[@class='cart_block_product_name']")).getAttribute("title"));
	}
	
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 														Helpers
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void VerifyExpectedItems(String expectedPrice, String expectedItemName)
	{
		Assert.assertEquals(expectedPrice, driver.findElement(By.xpath("//span[@id='our_price_display']")).getText());
		Assert.assertEquals(expectedItemName, driver.findElement(By.xpath("//h1[@itemprop='name']")).getText());
	}
	
	public static void OrderItemByIndexFromMainPage(int index)
	{
		// after going from checkout to main page, elements in list go bad. just renew the list each time this is called instead of try/catch on move to element
		SetupTestLists(); 
		
		// move cursor to selection 
		Actions action = new Actions(driver);
		action.moveToElement(eleList.get(index - 1)).perform(); 

		// select add to cart
		WaitForElementClickable(By.xpath(".//*[@id='homefeatured']/li[" +  index +  "]/div/div[2]/div[2]/a[1]/span"), 7, "");
		driver.findElement(By.xpath(".//*[@id='homefeatured']/li[" +  index +  "]/div/div[2]/div[2]/a[1]/span")).click();
		
		// select to checkout in pop-up 
		WaitForElementClickable(By.xpath("//span[contains(text(),'Proceed to checkout')]"), 7, "");
		driver.findElement(By.xpath("//span[contains(text(),'Proceed to checkout')]")).click();
		
		// wait for 'Proceed to checkout' in shop cart 
		WaitForElementClickable(By.xpath("(//span[contains(text(),'Proceed to checkout')])[2]"), 5, "");
		////a[@title='Continue shopping']
		
	}

	public static void ReturnToMainPageFromShoppingCart() throws Exception
	{
		WaitForElementClickable(By.xpath("//a[@title='Continue shopping']"), 5, "");
		driver.findElement(By.xpath("//a[@title='Continue shopping']")).click();
		WaitForMainPageToLoad();		
	}


	
	
	
}
 