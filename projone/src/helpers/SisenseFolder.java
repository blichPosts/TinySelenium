package helpers;

import java.util.ArrayList;
import java.util.List;

public class SisenseFolder {
	public String m_FolderName = "";
	public String m_oid = "";
	public String m_ParentFolder = "";
	public int m_level = -1; 
	
	public SisenseFolder(String folderName, String oid, String parentFolder, int level) {
		m_FolderName = folderName;
		m_oid = oid;
		m_ParentFolder = parentFolder;
		m_level = level;
	}
	
	
	/*
	public void Show() {
		System.out.println("-------------------------");
		System.out.println("Folder Name:" + m_FolderName);
		System.out.println("ParentName:" + m_ParentName);
		if(listOfChildFolders.size() == 0) {
			System.out.println("No child folders");
		}
		else {
			System.out.println("Child folders");
			for(String str : listOfChildFolders) {System.out.println(str);}			
		}
		System.out.println("ParentId:" + m_parentId);
		System.out.println("Oid:" + m_oid);
	}
	*/
}
