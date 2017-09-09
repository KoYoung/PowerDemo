package org.powerSystem.service.dept;

import java.util.List;

import org.powerSystem.entity.ShareDeptement;

public interface ShareDeptementService {

	public String query(ShareDeptement dept);
	
	public int saveDept(ShareDeptement dept);

	public void deleteDept(Integer deptId);
}
