package org.powerSystem.web.dept.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.powerSystem.entity.ShareDeptement;
import org.powerSystem.entity.ShareStaff;
import org.powerSystem.service.dept.ShareStaffService;
import org.powerSystem.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("struts-default")
@Namespace("/dept")
public class StaffInfoAction extends BaseAction {
	@Autowired
	private ShareStaffService staffService;
	private ShareStaff staff;
	private Integer deptid;
	private String ids;

	@Action("getStaffInfo")
	public String getStaffInfo() {
		super.writer(staffService.query(staff, getPage(), getRows()));
		return null;
	}
	@Action("getEmpTrees")
	public String getEmployeeTree(){
		super.writer(staffService.queryForTree(deptid));
		return null;
	}
	
	@Action("addStaff")
	public String addStaff() {
		try {
			if (deptid == null) {
				super.writer("error");
			} else {
				ShareDeptement dept = new ShareDeptement();//员工要加载上部门，
				dept.setDeptId(deptid);//前台页面传递的变量是deptid，没有封装对象，在这里封装一下
				staff.setShareDeptement(dept);
				staffService.saveStaff(staff);
				super.writer("success");
			}
		} catch (Exception e) {
			super.writer(e.getMessage());
		}
		return null;
	}
	@Action("delStaff")
	public String delStaff() {
		try {
			staffService.deleteStaff(ids);
			super.writer("success");
		} catch (Exception e) {
			super.writer(e.getMessage());
		}
		
		return null;
	}
	@Action("lockStaff")
	public String lockStaff(){
		try {
			staffService.updateLock(ids);
			super.writer("success");
		} catch (Exception e) {
			super.writer(e.getMessage());
		}
		return null;
	}
	
	@Action("deptRemove")
	public String deptRemove(){
		System.out.println(ids+"===="+deptid);
		try {
			staffService.updateDeptRemove(ids, deptid);
			super.writer("success");
		} catch (Exception e) {
			super.writer("错误信息："+e.getMessage());
		}
		
		return null;
	}
	
	
	
	
	public ShareStaff getStaff() {
		return staff;
	}

	public void setStaff(ShareStaff staff) {
		this.staff = staff;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
