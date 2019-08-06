package helpers;

import java.util.ArrayList;
import java.util.List;

// This is a data set found in the sisense API call '/elasticubes/servers/next/LocalHost/Cloud_Analytics'. It contains the tables and each table's associated column(s)    

public class SisenseDataSet {
	public String m_name = "";
	String m_oid = "";
	//public boolean m_foundTable;
	
	public List<SisenseTable> listOfTables = new ArrayList<SisenseTable>(); // list of sisense tables in data set 
	
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
			 	// need to return list of strings that are the column names
				return table.getSisenseColumnNames();
			}
		}
		return null;
	}
	
	// see if sisense table name is found in data set
	public boolean searchForSisenseTable(String tableName) {
		for(SisenseTable table: listOfTables) {
			if(table.m_name.equals(tableName)) {
				return true;
			}
		}
		return false;
	}
	
	
	public List<SisenseColumn> getSisenseColumnsForSisenseTable(String tableName) {
		for(SisenseTable table: listOfTables) {
			if(table.m_name.equals(tableName)) {
				return table.getSisenseColumns();
			}
		}
		return null;
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
