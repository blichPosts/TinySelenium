package helpers;

import java.util.Date;

public class CalendarPropertiesForSelection 
{
	public static String currentMonth =  "";
	public static String checkInMonth =  "";
	public static String checkOutMonth =  "";
	public static int currentDay = -1;
	public static int checkInDay = -1;	
	public static int checkOutDay = -1;
	public static String dateCheckin;
	public static String dateCheckOut;	
	
	
	public static void ShowTimes()
	{
		System.out.println("current month " + currentMonth);
		System.out.println("current day " + currentDay);
		System.out.println("checkin month " + checkInMonth);
		System.out.println("checkin day " + checkInDay);
		System.out.println("checkout month " + checkOutMonth);
		System.out.println("checkoutday " + checkOutDay);
		System.out.println("checkin Date " + dateCheckin);
		System.out.println("checkout Date " + dateCheckOut);		
	}
	
	public static void Foo()
	{
		
	}
	
}
