package helpers;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItem 
{
	public String m_name = "";
	public String m_color = "";
	public int m_quantity = 0;
	public double m_price = 0.0;
	public double m_TotalPrice = 0.0;
	public int m_MainPageIndex = -1;
	public static List<ShoppingCartItem> listOfShoppingCartItems = new ArrayList<ShoppingCartItem>(); 
	public static List<ShoppingCartItem> listOfShoppingCartItemsPulldownList =  new ArrayList<ShoppingCartItem>();
	
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
		m_quantity = 1;
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
	
	
}
