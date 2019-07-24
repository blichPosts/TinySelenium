package helpers;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTable {

	public String m_tableName = "";
	public List<Fields> listOfFieldNames; 
	
	public DatabaseTable(String name) {
		m_tableName = name;
		listOfFieldNames = new ArrayList<Fields>();
	}
	
	public void addFields(String fieldName, String fieldAlias){
		listOfFieldNames.add(new Fields(fieldName, fieldAlias));
	}
	
	public void Show() {
		System.out.println("*********************");
		System.out.println("Data Base Name: " + m_tableName);
		System.out.println("Fields ---- ");
		for(Fields field : listOfFieldNames) {
			System.out.print("Field Name: " + field.m_fieldName + "\t");
			System.out.println("Field Alias: " + field.m_fieldAlias);
		}
	}
}
