package helpers;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
	String m_name;
	String m_oid;
	public List<Table> listOfTables = new ArrayList<Table>();
	
	public DataSet(String name, String oid) {
		m_name = name;
		m_oid = oid;
	}
	
	public void addTable(String tableName, List<String> columns){
		listOfTables.add(new Table(tableName, columns));
	}
	
	public List<String> searchForTable(String tableName) {
		for(Table table: listOfTables) {
			if(table.m_name.equals(tableName)) {
				return table.m_columns;
			}
		}
		return null;
	}
	
	
	public void Show() {
		System.out.println("----------------");
		System.out.println("Data Set Name: " + m_name);
		System.out.println("Date Set Oid: " + m_oid);
		for(Table table : listOfTables) {
			table.Show();
		}
	}
}
