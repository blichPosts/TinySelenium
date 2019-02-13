package orders;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.w3c.dom.NameList;

import helpers.ShoppingCartItem;
import tests.BaseSelenium;

public class Orders extends BaseSelenium
{
	public static String currentColor = "";
	public static String currentName = "";
	public static String currentPrice = "";
	
	public static List<WebElement> eleList = new ArrayList<WebElement>(); 
	public static List<WebElement> namesList = new ArrayList<WebElement>();
	public static List<WebElement> costList = new ArrayList<WebElement>();
	public static List<WebElement> localWebList =  new ArrayList<WebElement>();
	//public static List<ShoppingCartItem> listOfShoppingCartItems =  new ArrayList<ShoppingCartItem>();
	public static List<ShoppingCartItem> listOfShoppingCartItemsPulldownList =  new ArrayList<ShoppingCartItem>();	
	public static int maxNumItemsOnMainPage = 0; 
	
	
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

		// at this point, lists should be same size.
		Assert.assertEquals(eleList.size(), namesList.size());
		Assert.assertEquals(namesList.size(), costList.size());
		maxNumItemsOnMainPage = eleList.size();
		
		// show text lists
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
		Random rand = new Random();
		
		SetupTestLists();
		
		// select item, verify name and price text  of item selected, and add it to listOfShoppingCartItems in ShoppingCartItem class 
		/*
		SelectItemAndUpdateShopingCartItemList(rand);
		SelectItemAndUpdateShopingCartItemList(rand);
		SelectItemAndUpdateShopingCartItemList(rand);
		SelectItemAndUpdateShopingCartItemList(rand);
		SelectItemAndUpdateShopingCartItemList(rand);
		SelectItemAndUpdateShopingCartItemList(rand);
		SelectItemAndUpdateShopingCartItemList(rand);
		SelectItemAndUpdateShopingCartItemList(rand);
		*/
		
		String maxLoop = config.getProperty("SampleSize");
		
		for(int x = 0; x < Integer.valueOf(maxLoop); x++)
		{
			SelectItemAndUpdateShopingCartItemList(rand);
		}

		//ShoppingCartItem.ShowListFromCartAdditions();
		
		StoreCartPulldownItems();
		//ShoppingCartItem.ShowListFromCartAdditionsPulldown();
		
		ShoppingCartItem.CompareOrderLists();
		
		Thread.sleep(1000);
	}
	
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 														Helpers
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void StoreCartPulldownItems() throws InterruptedException
	{
		double tempDouble = 0;
		int tempInt = 0;

		// open pull-down with hover.
		WebElement pullDowm = driver.findElement(By.xpath("//b[contains(text(),'Cart')]"));
		Actions action = new Actions(driver);
		action.moveToElement(pullDowm).perform(); 
		Thread.sleep(1000);

		// store properties in each cart item
		List<WebElement> singleItemTotalCost = driver.findElements(By.xpath("//span[@class='price']"));		
		List<WebElement> quantities = driver.findElements(By.xpath("//span[@class='quantity']"));
		List<WebElement> productNames = driver.findElements(By.xpath("//a[@class='cart_block_product_name']"));
		List<WebElement> productAtributes = driver.findElements(By.xpath("//div[@class='product-atributes']/a"));

		// build list of items in pull-down of shopping cart items. 
		for(int x = 0; x < singleItemTotalCost.size(); x++)
		{
			// convert quantity and cost
			tempInt =  Integer.valueOf(quantities.get(x).getText());
			tempDouble =  Double.valueOf(singleItemTotalCost.get(x).getText().replace("$", ""));
			
			// add to list
			ShoppingCartItem.listOfShoppingCartItemsPulldownList.add(new ShoppingCartItem(productNames.get(x).getAttribute("title"), productAtributes.get(x).getText(), 
																						  tempInt, tempDouble));
		}		
	}
	
	public static void SelectItemAndUpdateShopingCartItemList(Random rand) throws Exception
	{
		String name = "";
		int itemIndex = rand.nextInt(maxNumItemsOnMainPage) + 1; // + 1 makes it between 1 and maxNumItemsOnMainPage
		
		System.out.println("Current index selkected from main page is: " + itemIndex);
		
		SetupTestLists(); // refresh these while in main page.
		
		// select an item in main page, store items from pop-up, and check items in the pop-up  
		OrderItemByIndexFromMainPage(itemIndex);
		UpdateShoppingCartListWithVerify(itemIndex, true);
		
		ReturnToMainPageFromShoppingCart();

		//SetupTestLists();
		//UpdateShoppingCartList(itemIndex);
	}

	// verify some values in current pop-up.
	public static void VerifyCurrentValuesInPopup(int index)
	{
		double tempDouble = 0.0;
		int tempInt = -1;
		
		// verify total price for item in pop-up
		tempDouble = Double.valueOf(driver.findElement(By.xpath("//span[@id='layer_cart_product_price']")).getText().replace("$",  ""));
		Assert.assertEquals(tempDouble, ShoppingCartItem.listOfShoppingCartItems.get(index).m_TotalPrice); // bladd round
		
		// verify quantity for item in pop-up
		tempInt = Integer.valueOf(driver.findElement(By.xpath("//span[@id='layer_cart_product_quantity']")).getText());
		Assert.assertEquals(tempInt, ShoppingCartItem.listOfShoppingCartItems.get(index).m_quantity);
		
		// verify total of all products shop cart items.
		//span[@class='ajax_block_products_total']
		tempDouble =  Double.valueOf(driver.findElement(By.xpath("//span[@class='ajax_block_products_total']")).getText().replace("$", ""));
		Assert.assertEquals(tempDouble, ShoppingCartItem.FullTotal());
	}
	
	
	// this is used when an item has been selected from main page and then 'add to cart' has been selected.
	// the index passed in is the index of the item selected on the main page.
	// boolean verify says whether to verify some pop-up info.
	public static void UpdateShoppingCartListWithVerify(int itemIndex, boolean verify)
	{
		int cntr = 0;
		boolean foundExistingItem = false;
		double tempDouble;
		
		// go through the list and see if item has already been added to the shopping cart 
		//for(ShoppingCartItem cartItem : listOfShoppingCartItems)
		for(ShoppingCartItem cartItem : ShoppingCartItem.listOfShoppingCartItems)			
		{
			if(itemIndex == cartItem.m_MainPageIndex)	
			{
				foundExistingItem = true;
				break;
			}
			cntr++;
		}
		
		if(foundExistingItem) // update quntity and total cost
		{
			ShoppingCartItem.listOfShoppingCartItems.get(cntr).m_quantity++;
			ShoppingCartItem.listOfShoppingCartItems.get(cntr).m_TotalPrice += ShoppingCartItem.listOfShoppingCartItems.get(cntr).m_price;
			
			//https://stackoverflow.com/questions/25981349/java-double-round-off-to-2-decimal-always
			tempDouble = Math.round(ShoppingCartItem.listOfShoppingCartItems.get(cntr).m_TotalPrice * 100D)/100D;
			ShoppingCartItem.listOfShoppingCartItems.get(cntr).m_TotalPrice = tempDouble;
		}
		else
		{
			try
			{
				ShoppingCartItem.listOfShoppingCartItems.add(new ShoppingCartItem(currentName, currentColor, Double.valueOf(costList.get(itemIndex - 1).getText().replace("$", "")), itemIndex));				
			}
			catch (Exception e)
			{
				ShowText("name: " + currentName + "color: " + currentColor);
				ShowText(e.getMessage());
				Assert.fail("See exception message about missing strings");
			}

		}

		if(verify)
		{
			VerifyCurrentValuesInPopup(cntr);			
		}
	}
	
	public static void VerifyExpectedItems(String expectedPrice, String expectedItemName)
	{
		Assert.assertEquals(expectedPrice, driver.findElement(By.xpath("//span[@id='our_price_display']")).getText());
		Assert.assertEquals(expectedItemName, driver.findElement(By.xpath("//h1[@itemprop='name']")).getText());
	}
	
	// this opens the cart window, by clicking 'add to cart' hover, that has information about the item selected in the main page.
	// some variables in the pop-up are stored away and name is verified from name in main page.
	// this orders the selection
	public static void OrderItemByIndexFromMainPage(int index)
	{
		// move cursor to selection 
		Actions action = new Actions(driver);
		action.moveToElement(eleList.get(index - 1)).perform(); 

		// select add to cart
		WaitForElementClickable(By.xpath(".//*[@id='homefeatured']/li[" +  index +  "]/div/div[2]/div[2]/a[1]/span"), 7, "");
		driver.findElement(By.xpath(".//*[@id='homefeatured']/li[" +  index +  "]/div/div[2]/div[2]/a[1]/span")).click();
		
		// wait for cart to open
		WaitForElementClickable(By.xpath("//span[contains(text(),'Proceed to checkout')]"), 7, "");

		// store name, color, and price. verify name by comparing to stored away name in names list
		currentName = driver.findElement(By.xpath("//span[@id='layer_cart_product_title']")).getText(); 
		Assert.assertEquals(currentName, namesList.get(index - 1).getText() ); // check the name is correct
		currentColor = driver.findElement(By.xpath("//span[@id='layer_cart_product_attributes']")).getText();
		currentPrice = driver.findElement(By.xpath("//span[@id='layer_cart_product_price']")).getText();		
	}

	public static void ReturnToMainPageFromShoppingCart() throws Exception
	{
		WaitForElementClickable(By.xpath("//div[@class='button-container']//span[1]//span[1]"), 5, "");
		driver.findElement(By.xpath("//div[@class='button-container']//span[1]//span[1]")).click();
		WaitForMainPageToLoad();		
	}
}
 