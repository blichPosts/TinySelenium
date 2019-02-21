package actions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import helpers.CalendarPropertiesForSelection;
import org.testng.*;
import tests.BaseSelenium;

public class Cruise extends BaseSelenium 
{
	enum CalendarType
	{
		checkIn,
		checkOut
	}
	
	public static void GetCalendartest() throws InterruptedException
	{
		driver.findElement(By.xpath("//input[@placeholder='Check in']")).click();
		Thread.sleep(1500);
		List<WebElement> eleList = driver.findElements(By.xpath("//tbody/tr/td[text()='1']"));
		ShowText("Month = " + driver.findElement(By.xpath("(//th[@class='switch'])[1]")).getText());
		
		for(WebElement ele : eleList)
		{
			if(ele.isDisplayed())
			{
				ShowText(ele.getText());
			}
		}
	}
	
	public static void SetupStartEndDates() throws ParseException
	{
		LocalDateTime now = LocalDateTime.now();

		System.out.println(now);
		
		CalendarPropertiesForSelection.currentMonth = now.getMonth().toString().toLowerCase();
		CalendarPropertiesForSelection.currentDay = now.getDayOfMonth();

		now = now.plusDays(17);
		CalendarPropertiesForSelection.checkInMonth = now.getMonth().toString().toLowerCase();
		CalendarPropertiesForSelection.checkInDay = now.getDayOfMonth();
		//Date dateCheckIn = Date.from( now.atZone( ZoneId.systemDefault()).toInstant()); // convert localDateTime to Date		
		now = LocalDateTime.now();
		
		now = now.plusDays(66);
		CalendarPropertiesForSelection.checkOutMonth = now.getMonth().toString().toLowerCase();
		CalendarPropertiesForSelection.checkOutDay = now.getDayOfMonth();
		//Date dateCheckOut = Date.from( now.atZone( ZoneId.systemDefault()).toInstant()); // convert localDateTime to Date
		
		// format messed up ?
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Date dateCheckOut2  =  sdf.parse(sdf.format(dateCheckOut)); 
		//Date dateCheckIn2  =  sdf.parse(sdf.format(dateCheckIn));		
		//CalendarPropertiesForSelection.dateCheckin =  dateCheckIn2;
		//CalendarPropertiesForSelection.dateCheckOut =  dateCheckOut2;		
		
		CalendarPropertiesForSelection.ShowTimes();

	}
	
	// bladd
	public static void MakeDateSelections() throws InterruptedException
	{
		// wait for search button and date picker that will be clicked.
		WaitForElementClickable(By.xpath("//button[contains(text(),'Search')]"), 5, ""); 
		WaitForElementClickable(By.xpath("//input[@placeholder='Check in']"), 5, "");
		
		driver.findElement(By.xpath("//input[@placeholder='Check in']")).click(); // bring up check-in date picker
		Thread.sleep(1500);

		// make sure month is correct
		SetupSelectedMonth(CalendarPropertiesForSelection.checkInMonth, CalendarType.checkIn);
		SelectDate(String.valueOf(CalendarPropertiesForSelection.checkInDay));
		
		Thread.sleep(15000);
		
		SetupSelectedMonth(CalendarPropertiesForSelection.checkOutMonth, CalendarType.checkOut);
		SelectDate(String.valueOf(CalendarPropertiesForSelection.checkOutDay));
	}
	
	public static void SelectDate(String dayNumber)
	{
		List<WebElement> eleList = driver.findElements(By.xpath("//tbody/tr/td[text()='" + dayNumber + "']"));
		List<WebElement> eleListFiltered = new ArrayList<WebElement>();
		
		for(WebElement ele : eleList)
		{
			if(ele.isDisplayed())
			{
				eleListFiltered.add(ele);
			}
		}		
		
		if(eleListFiltered.size() > 2)
		{
			Assert.fail("Found too many dates in calendar");
		}
		
		if(eleListFiltered.size() > 1)
		{
			eleListFiltered.get(1).click();
		}
		else
		{
			eleListFiltered.get(0).click();
		}
	}
	
	// https://stackoverflow.com/questions/1086396/java-date-month-difference
	public static void SetupSelectedMonth(String monthToMoveTo, CalendarType type) throws InterruptedException
	{
		String xpath  = "";
		
		if(type.equals(CalendarType.checkIn))
		{
			xpath = "(//th[@class='next'])[1]";
		}
		else
		{
			xpath = "(//th[@class='next'])[4]";
		}
		
		
		for(int x = 0; x < 7; x++)
		{
			if(GetMonthFromCheckinCalendar(type).equals(monthToMoveTo))
			{
				break;
			}
			else
			{
				WaitForElementClickable(By.xpath(xpath), 3, "");
				driver.findElement(By.xpath(xpath)).click();
				Thread.sleep(2000);
			}
		}
	}
	
	// bladd
	public static String GetMonthFromCheckinCalendar(CalendarType type)
	{
		if(type.equals(CalendarType.checkIn))
		{
			return driver.findElement(By.xpath("(//th[@class='switch'])[1]")).getText().toLowerCase().split(" ")[0].trim();			
		}
		else
		{
			return driver.findElement(By.xpath("(//th[@class='switch'])[4]")).getText().toLowerCase().split(" ")[0].trim();
		}
	}

	
	public static void DatePractice() throws ParseException
	{
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 30);
		date = calendar.getTime();
		
		System.out.println(dateFormat.format(date));
		
		//CalendarPropertiesForSelection.ShowTimes();

	}

	
	

	// https://stackoverflow.com/questions/1086396/java-date-month-difference
	public static final long getMonthsDifference(Date date1, Date date2) 
	{
	    YearMonth m1 = YearMonth.from(date1.toInstant());
	    YearMonth m2 = YearMonth.from(date2.toInstant());

	    return m1.until(m2, ChronoUnit.MONTHS) + 1;
	}

	// LEMON
	public static final long GetMonthsDifference(Date date1, Date date2) 
	{
	    YearMonth m1 = YearMonth.from(date1.toInstant());
	    YearMonth m2 = YearMonth.from(date2.toInstant());

	    return m1.until(m2, ChronoUnit.MONTHS) + 1;
	}	
	
	
}
