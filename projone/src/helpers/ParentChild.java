package helpers;

import java.util.ArrayList;
import java.util.List;

public class ParentChild {
	String m_ParentFolder = "";
	List<String> m_ChildFolderList = new ArrayList<String>(); 
	List<String> m_ChildFolderOidList = new ArrayList<String>();
	List<String> tempList = new ArrayList<String>();
	
	public ParentChild(String parentFolder, String childFolder, String childFolderOid) {
		m_ParentFolder = parentFolder;
		m_ChildFolderList.add(childFolder);
		m_ChildFolderOidList.add(childFolderOid);
	}
	
	public void update(String childFolder, String childOid) {
		m_ChildFolderList.add(childFolder);
		m_ChildFolderOidList.add(childOid);
	}
	
	public List<String> getChildrenForFolder(String parent) {
		tempList.clear();
		for(String str: m_ChildFolderList) {
			tempList.add(str);
		}
		return tempList;
	}
	
	public void Show() {
		System.out.println("Parent folder = " + m_ParentFolder);
		System.out.print("Children Folders = ");
		for(String str : m_ChildFolderList) {
			System.out.print(str + ":");
		}
		System.out.println("");
	}
	
	
	
}
