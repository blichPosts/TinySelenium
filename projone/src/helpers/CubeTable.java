package helpers;

import java.util.ArrayList;
import java.util.List;

public class CubeTable {
	public String m_name = "";
	List<String> sisenseTableColumns;
	
	CubeTable(String name){
		sisenseTableColumns = new ArrayList<String>();
		m_name = name.trim();
	}
	
	public void addColumn(String column) {
		sisenseTableColumns.add(column.trim());
	}
	
}
