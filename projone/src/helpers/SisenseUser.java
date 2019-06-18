package helpers;

public class SisenseUser {
	String m_userName = "";
	String m_id = "";
	
	public SisenseUser(String userName, String id) {
		m_id = id;
		m_userName = userName;
	}
	
	public void show(){
		System.out.println("---------------------");
		System.out.println("Name: " + m_userName);
		System.out.println("Id: " + m_id);
	}
}
