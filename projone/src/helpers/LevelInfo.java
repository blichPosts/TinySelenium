package helpers;

import java.util.ArrayList;
import java.util.List;

public class LevelInfo {
	
	int m_level = -1;
	List<ParentChild> parentChildList;
	List<String> allFoldersAtThisLevelPassedIn = new ArrayList<String>(); // all folders at this level - raw list.
	List<String> allFoldersAtThisLevel = new ArrayList<String>(); // all folders at this level cleaned up into a list
	List<String> tempList = new ArrayList<String>();
	
	// constructor
	public LevelInfo(int level, List<String> folderList) {  // note !!! also record (pass in) oids for all folders
		m_level = level; // level number
		parentChildList = new ArrayList<ParentChild>();
		// this creates a clean list of the folders at this level
		allFoldersAtThisLevelPassedIn.addAll(folderList); 
		for(String str : allFoldersAtThisLevelPassedIn) { // take out empty items from list passed in.
			if(str.length() != 0) {
				allFoldersAtThisLevel.add(str);
			}
		}
	}

	// pass in a folder and return the child folder(s) in a list.
	public List<String> getChildrenforParent(String parent) {
		tempList.clear();
		for(ParentChild pChild : parentChildList) {
			if(pChild.m_ParentFolder.equals(parent)) {
				tempList = pChild.getChildrenForFolder(parent); // found folder passed in, get children for the folder
				break;
			}
		}
		return tempList;
	}
	
	
	public void AddParentChild(String parentFolder, String childFolder, String childFolderOid) {
		boolean updateOnly = false;
		
		// go through list and see if parent already exists.
		for(ParentChild pChild : parentChildList) {
			if(pChild.m_ParentFolder.equals(parentFolder)) {
				pChild.update(childFolder, childFolderOid);
				updateOnly = true;
				break;
			}
		}
		
		if(!updateOnly) {
			parentChildList.add(new ParentChild(parentFolder, childFolder, childFolderOid));
		}
	}
	
	public void Show() {
		System.out.println("Level = " + m_level + " --------------------");
		//System.out.println("parent/child list size = " + parentChildList.size());
		System.out.println("All folders at this level " + allFoldersAtThisLevel);
		for(ParentChild pChild : parentChildList) {
			pChild.Show();
		}
	}
	
	public List<String> getAllFoldersAtThisLevel() {
		return allFoldersAtThisLevel;
	}
	
	
}
