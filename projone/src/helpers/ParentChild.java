package helpers;

import java.util.ArrayList;
import java.util.List;

public class ParentChild {
	String m_ParentFolder = "";
	List<String> m_ChildFolderList = new ArrayList<String>(); 
	List<String> m_ChildFolderOidList = new ArrayList<String>();
	
	public ParentChild(String parentFolder, String childFolder, String childFolderOid) {
		m_ParentFolder = parentFolder;
		m_ChildFolderList.add(childFolder);
		m_ChildFolderOidList.add(childFolderOid);
	}
	
	public void update(String childFolder, String childOid) {
		m_ChildFolderList.add(childFolder);
		m_ChildFolderOidList.add(childOid);
	}
	
	public void Show() {
		System.out.println("---------------");
		System.out.println(m_ParentFolder);
	}
	
	
	
}
