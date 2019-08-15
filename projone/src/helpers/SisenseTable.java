package helpers;

import java.util.ArrayList;
import java.util.List;

public class SisenseTable {
	
	public String m_name = ""; 
	public boolean m_tableFound;
	List<SisenseColumn> m_columns;
	
	public SisenseTable(String tableName, List<String> columns) {
		m_name = tableName;
		m_columns = new ArrayList<SisenseColumn>();
		m_tableFound = false;

		for(String string : columns) { 
			m_columns.add(new SisenseColumn(string));
		}
	}
	
	public List<String> getSisenseColumnNames(){
		List<String> tempList = new ArrayList<String>() ;
		for(SisenseColumn column : m_columns) {
			tempList.add(column.m_Name);
		}
		return tempList;
	}
	
	public List<SisenseColumn> getSisenseColumns(){
		return m_columns;
	}
	
	
	public void Show() {
		System.out.println("Sisense Cube Table Name: " + m_name);
		System.out.print("Sisense Cube Columns: ");
		System.out.print(getSisenseColumnNames());
		System.out.println("");
	}
}
