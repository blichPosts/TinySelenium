package helpers;

import java.util.ArrayList;
import java.util.List;

public class LevelInfo {
	
	int m_level = -1;
	List<ParentChild> parentChildList;
	
	public LevelInfo(int level) {
		m_level = level;
		parentChildList = new ArrayList<ParentChild>();
	}

	public void AddParentChild(String parentFolder, String childFolder, String childOFolderOid) {
		boolean updateOnly = false;
		
		// go through list and see if parent already exists.
		for(ParentChild pChild : parentChildList) {
			if(pChild.m_ParentFolder.equals(parentFolder)) {
				pChild.update(childFolder, childOFolderOid);
				updateOnly = true;
				break;
			}
		}
		
		if(!updateOnly) {
			parentChildList.add(new ParentChild(parentFolder, childFolder, childOFolderOid));			
		}
	}
	
	public void Show() {
		System.out.println("Level = " + m_level);
		for(ParentChild pChild : parentChildList) {
			pChild.Show();
		}
	}
	
}
