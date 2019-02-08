package helpers;

public class ShoppingCartItem 
{
	public String m_name = "";
	public String m_color = "";
	public int m_quanity = 0;
	public double m_price = 0.0;
	
	public ShoppingCartItem(String name, String color, double price)
	{
		m_name = name;
		m_color = color;
		m_price = price;
		m_quanity = 1;
	}
	
	public void Show()
	{
		System.out.println("-------" + m_name);
		System.out.println("name " + m_name);
		System.out.println("color " + m_color);
		System.out.println("price " + m_price);
		System.out.println("quanity " + m_quanity);
	}
}
