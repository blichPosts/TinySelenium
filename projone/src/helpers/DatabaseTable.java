package helpers;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTable {

	public String m_tableName = "";
	public List<String> columnNames; 
	
	public DatabaseTable(String name) {
		m_tableName = name;
		columnNames = new ArrayList<String>();
	}
	
	public void addColumnName(String columnName){
		columnNames.add(columnName);
	}
	
	public void Show() {
		System.out.println("*********************");
		System.out.println("Data Base Name: " + m_tableName);
		System.out.println("Column Names:");
		for(String str : columnNames) {
			System.out.println(str);
		}
		
	}
	
 
	
	
}
