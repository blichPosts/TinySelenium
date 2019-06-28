package helpers;

import java.util.ArrayList;
import java.util.List;

public class SisenseFolder {
	public String m_parentId = null;
	public String m_oid = "";
	public int m_level = -1; 
	public String m_FolderName = "";
	public String m_ParentName = "";
	List<String> listOfChildFolders;
	
	public void update(String parentIdSearch ) {
		
	}
	
	public SisenseFolder(String parentId, String oid, String name) {
		m_parentId = parentId;
		m_oid = oid;
		m_FolderName = name;
		listOfChildFolders = new ArrayList<String>();
	}
	
	// 
	public void doExternalQuery(int level, SisenseFolder siFolderIn) {
		if(siFolderIn.m_oid.equals(m_parentId)) {
			siFolderIn.listOfChildFolders.add(m_FolderName);
		}
	}
	
	public void Show() {
		System.out.println("-------------------------");
		System.out.println("ParentId:" + m_parentId);
		System.out.println("ParentName:" + m_ParentName);
		System.out.println("Folder Name:" + m_FolderName);
		System.out.println("Oid:" + m_oid);
		System.out.println("Level:" + m_level);
	}
}
