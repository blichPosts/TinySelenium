package helpers;

import java.util.ArrayList;
import java.util.List;

// This is a data set found in the sisense API call '/elasticubes/servers/next/LocalHost/Cloud_Analytics'. It contains the tables and each table's associated column(s)    

public class SisenseDataSet {
	public String m_name = "";
	String m_oid = "";
	//public boolean m_foundTable;
	
	public List<SisenseTable> listOfTables = new ArrayList<SisenseTable>();
	
	public SisenseDataSet(String name, String oid) {
		m_name = name;
		m_oid = oid;
		//m_foundTable = false;
	}
	
	public void addTable(String tableName, List<String> columns){
		listOfTables.add(new SisenseTable(tableName, columns)); // BROKE !!!
	}
	
	public List<String> searchForSisenseTableAndReturnColumns(String tableName) {
		for(SisenseTable table: listOfTables) {
			if(table.m_name.equals(tableName)) {
			 	// need to return list of strings
				
				
				
				
			}
		}
		return null;
	}
	
	public boolean searchForSisenseTable(String tableName) {
		for(SisenseTable table: listOfTables) {
			if(table.m_name.equals(tableName)) {
				return true;
			}
		}
		return false;
	}
	
	//public void setTableFound(String tableName) {
	//	m_foundTable = true;
	//}
	
	
	public void Show() {
		System.out.println("----------------");
		System.out.println("Data Set Name: " + m_name);
		System.out.println("Date Set Oid: " + m_oid);
		for(SisenseTable table : listOfTables) {
			table.Show();
		}
	}
}
