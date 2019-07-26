package helpers;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	String m_name = ""; 
	List<String> m_columns;
	
	public Table(String tableName, List<String> columns) {
		m_name = tableName;
		m_columns = new ArrayList<String>();
		m_columns.addAll(columns);
	}
	
	public void Show() {
		System.out.println("Sisense Cube Table Name: " + m_name);
		System.out.print("Sisense Cube Columns: ");
		for(String column : m_columns) {
			System.out.print(column + ", ");
		}
		System.out.println("");
	}
}
