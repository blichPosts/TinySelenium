package helpers;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTable {

	public String m_tableName = "";
	public boolean m_tableFound; // zzz
	public List<Fields> listOfFieldNames; 
	
	public DatabaseTable(String name) {
		m_tableName = name;
		m_tableFound = false;
		listOfFieldNames = new ArrayList<Fields>();
	}
	
	public void addFields(String fieldName, String fieldAlias){
		listOfFieldNames.add(new Fields(fieldName, fieldAlias));
	}

	public void setupSisenseFields() {
		for(Fields fields: listOfFieldNames) {
			fields.setupSisenseField();
		}
	}
	
	public void clearTableFound() { // zzz
		m_tableFound = false;
	}
	
	public void setTableFound() { // zzz
		m_tableFound = true;
	}
	
	
	public void Show() {
		System.out.println("*********************");
		System.out.println("Data Base Table Name: " + m_tableName); // zzz
		System.out.println("Fields ---- ");
		for(Fields field : listOfFieldNames) {
			System.out.print("Field Name: " + field.m_fieldName + "\t");
			System.out.println("Field Alias: " + field.m_fieldAlias);
		}
	}
}
