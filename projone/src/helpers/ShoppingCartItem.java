package helpers;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class ShoppingCartItem 
{
	public String m_name = "";
	public String m_color = "";
	public int m_quantity = 0;
	public double m_price = 0.0;
	public double m_TotalPrice = 0.0;
	public int m_MainPageIndex = -1;
	public static List<ShoppingCartItem> listOfShoppingCartItems = new ArrayList<ShoppingCartItem>();  // list from main page add to carts
	public static List<ShoppingCartItem> listOfShoppingCartItemsPulldownList =  new ArrayList<ShoppingCartItem>(); // list in cart products hover
	
	public ShoppingCartItem(String name, String color, double price, int index)
	{
		m_MainPageIndex = index;
		m_name = name;
		m_color = color;
		m_price = price;
		m_quantity = 1;
		m_TotalPrice = price;
	}
	
	public ShoppingCartItem(String name, String color, int quantity, double itemTotal)
	{
		m_name = name;
		m_color = color;
		m_quantity = quantity;
		m_TotalPrice = itemTotal;
	}
	
	public static void ShowListFromCartAdditions()
	{
		for(ShoppingCartItem cartItem : listOfShoppingCartItems)
		{
			System.out.println("----------");
			System.out.println("name " + cartItem.m_name);
			System.out.println("color " + cartItem.m_color);
			System.out.println("price " + cartItem.m_price);
			System.out.println("quanity " + cartItem.m_quantity);
			System.out.println("total " + cartItem.m_TotalPrice);			
		}
	}
	
	public static void ShowListFromCartAdditionsPulldown()
	{
		for(ShoppingCartItem cartItem : listOfShoppingCartItemsPulldownList)
		{
			System.out.println("----------");
			System.out.println("name " + cartItem.m_name);
			System.out.println("color " + cartItem.m_color);
			//System.out.println("price " + cartItem.m_price);
			System.out.println("quanity " + cartItem.m_quantity);
			System.out.println("total " + cartItem.m_TotalPrice);			
		}
	}
	
	public static double FullTotal()
	{
		double tempDouble = 0.0;
		
		for(ShoppingCartItem cartItem : listOfShoppingCartItems)
		{
			tempDouble += cartItem.m_TotalPrice;
		}
		//https://stackoverflow.com/questions/25981349/java-double-round-off-to-2-decimal-always
		return Math.round(tempDouble * 100D)/100D ;
	}
	
	public static boolean CompareObjects(ShoppingCartItem itemOne, ShoppingCartItem itemTwo)
	{
		if(itemOne.m_name.equals(itemTwo.m_name) && itemOne.m_color.equals(itemTwo.m_color) && itemOne.m_quantity == itemTwo.m_quantity && itemOne.m_TotalPrice == itemTwo.m_TotalPrice)
		{
			return true;
		}
		return false;
	}
	
	
	public static void ShowCartItem(ShoppingCartItem cartItem, boolean showTitle)
	{
		if(showTitle)
		{
			System.out.println("Cart Item ****** ");			
		}

		System.out.println("name " + cartItem.m_name);
		System.out.println("color " + cartItem.m_color);
		//System.out.println("price " + cartItem.m_price);
		System.out.println("quanity " + cartItem.m_quantity);
		System.out.println("total " + cartItem.m_TotalPrice);			
	}
	
	public static void CompareOrderLists()
	{
		boolean foundMatch = false;
		
		Assert.assertEquals(listOfShoppingCartItems.size(), listOfShoppingCartItemsPulldownList.size());
		
		for(ShoppingCartItem cartItem : listOfShoppingCartItems) // outer loop. items selected on main page.
		{
			foundMatch = false;			

			// this loops through the pull down list and looks for the current item taken from the list of items selected in the main page 
			for(ShoppingCartItem cartItemInner : listOfShoppingCartItemsPulldownList) // inner loop
			{
				if(CompareObjects(cartItem, cartItemInner))
				{
					foundMatch = true;
				}
			}
			
			// see if a match was found
			if(foundMatch == false) // item not found in inner loop.
			{
				System.out.println("*** Failed selectiom item with main page index = " + cartItem.m_MainPageIndex);
				ShowCartItem(cartItem, false);
				Assert.fail("failed verification for current item.");
			}
			else
			{
				System.out.println("*** Found Item");				
			}
		}
	}
}
