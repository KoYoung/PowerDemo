package org.powerSystem.util;

import java.util.Comparator;

import org.powerSystem.entity.ShareDeptement;

public class ComparatorDept implements Comparator {

	public int compare(Object arg0, Object arg1) {
		ShareDeptement info1 = (ShareDeptement) arg0;
		ShareDeptement info2 = (ShareDeptement) arg1;
		if (info1.getDeptSort() != null) {
			return info1.getDeptSort().compareTo(info2.getDeptSort());
		} else {
			return 0;
		}
	}

}