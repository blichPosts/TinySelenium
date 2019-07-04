package helpers;

import java.util.ArrayList;
import java.util.List;

public class Dashboard {

	public String m_Name = "";
	public String m_Oid = "";
	public List<String> titleList;
	
	public Dashboard(String name, String oid) {
		titleList = new ArrayList<String>(); // initialize to hold titles that go with this dash board
		m_Name = name;
		m_Oid = oid;
	}
	
	public void addTitle(String title) {
		titleList.add(title);
	}
	
	public void Show() {
		
	}
	
	
}
