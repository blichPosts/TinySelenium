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
	
	public void addTable(String name, String oid){
		
	}
	
	
	public void Show() {
		System.out.println("----------------");
		System.out.println("Name: " + m_name);
		System.out.println("Oid: " + m_oid);
	}
}
