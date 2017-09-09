package org.powerSystem.util;

import java.util.Comparator;

import org.powerSystem.entity.ShareActioninfo;

public class ComparatorAction implements Comparator {

	public int compare(Object arg0, Object arg1) {
		ShareActioninfo info1 = (ShareActioninfo) arg0;
		ShareActioninfo info2 = (ShareActioninfo) arg1;
		if(info1.getActionSort()!=null){
		return info1.getActionSort().compareTo(info2.getActionSort());
		}else{
			return 0;
		}
	}

}