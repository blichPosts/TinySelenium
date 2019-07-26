package helpers;

public class Fields {
	
	public String m_fieldName = "";
	public String m_fieldAlias = "";	
	public String m_sisenseField = "";
	
	Fields(String name, String alias){
		m_fieldName = name;
		m_fieldAlias = alias;
	}
	
	public void setupSisenseField() {
		if(!m_fieldAlias.equals("")) {
			m_sisenseField = m_fieldAlias;
		}
		else {
			m_sisenseField = m_fieldName;
		}
	}	
	
}
