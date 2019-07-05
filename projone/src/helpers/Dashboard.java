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
		System.out.println("----------------------------");
		System.out.println("Dash name " + m_Name);
		System.out.print("Dash titles:");
		if(titleList.size() == 0) {
			System.out.println("Dash has no titles.");
		}
		else {
			for(String str : titleList) {
				System.out.print(str);
				System.out.print(", ");
			}
			System.out.println("");
		}
	}
}
