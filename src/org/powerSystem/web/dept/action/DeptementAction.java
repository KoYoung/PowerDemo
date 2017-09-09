package org.powerSystem.web.dept.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.powerSystem.entity.DeptUtil;
import org.powerSystem.entity.ShareDeptement;
import org.powerSystem.service.dept.ShareDeptementService;
import org.powerSystem.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("struts-default")
@Namespace("/dept")
public class DeptementAction extends BaseAction {

	private ShareDeptement dept;
	@Autowired
	private ShareDeptementService deptService;

	@Action("getAllDepts")
	public String getAllDepts() {
		super.writer(deptService.query(dept));
		return null;
	}
	@Action("addDeptInfo")
	public String addDeptInfo(){
		try {
			int index = deptService.saveDept(dept);
			super.writer("{\"msg\":\"success\",\"index\":" + index + "}");
		} catch (Exception e) {
			super.writer("{\"msg\":\""+e.getMessage()+"\",\"index\":0}");
		}
		return null;
	}
	
	@Action("deletedept")
	public String deletePermit() {
		if (dept != null) {
			try {
				deptService.deleteDept(dept.getDeptId());
				super.writer("success");
			} catch (Exception e) {
				super.writer("error"+e.getMessage());
			}
		} 
		return null;
	}

	public ShareDeptement getDept() {
		return dept;
	}

	public void setDept(ShareDeptement dept) {
		this.dept = dept;
	}
}
