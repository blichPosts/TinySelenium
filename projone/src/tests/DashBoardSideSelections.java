package tests;

import javax.swing.JOptionPane;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import actions.*;

public class DashBoardSideSelections extends BaseSelenium
{
		@BeforeClass
		public static void setUp()    throws Exception
		{
			SetupDiver();
			SetupConfig();
		}
		
		@Test
		public void FooBar() throws Exception 
		{
			
			DashBoardWithSideSelections.GetURL();
			DashBoardWithSideSelections.Login();
			DashBoardWithSideSelections.SideStuff();
			
			/*
			DashBoardWithSideSelections.SelectThemes();

			DashBoardWithSideSelections.SelectNewOrder();
			DashBoardWithSideSelections.WorkWithLeftPane();
			*/
			
			ShowText("Test Done");
			
			//DashBoardWithSideSelections.LogOut();
		}
		
		@AfterClass
		public void Finish() 
		{
			JOptionPane.showConfirmDialog(null, "MM");
			driver.close();
			driver.quit();
		}
}
