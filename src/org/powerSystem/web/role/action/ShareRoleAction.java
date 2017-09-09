package org.powerSystem.web.role.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.powerSystem.entity.ShareRole;
import org.powerSystem.service.role.ShareRoleService;
import org.powerSystem.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("struts-default")
@Namespace("/role")
public class ShareRoleAction extends BaseAction {
	@Autowired
	private ShareRoleService roleService;
	private ShareRole role;
	private String ids;
	@Action("getRoles")
	public String getRoles(){
		super.writer(roleService.query());
		return null;
	}
	@Action("addRoles")
	public String addRoles() {
		try {
			int index = roleService.saveRole(role);
			super.writer("{\"msg\":\"success\",\"index\":" + index + "}");
		} catch (Exception e) {
			super.writer("{\"msg\":\"error\",\"index\":0}");
		}
		return null;
	}
	@Action("deleteRole")
	public String deletePermit() {
		if (role != null) {
			roleService.deleteRole(role.getRoleId());
			super.writer("success");
		} else {
			super.writer("error");
		}
		return null;
	}
	@Action("allotmentPermit")
	public String allotmentPermit(){
		try {
			roleService.update(role,ids);
			super.writer("success");
		} catch (Exception e) {
			super.writer("角色配置错误"+e.getMessage());
		}
		
		return null;
	} 
	@Action("getRolesByid")
	public String getRolesByid(){
		try {
			//通过角色ID取权限的ID集合
			String info = roleService.query(role.getRoleId());
			super.writer(info);
		} catch (Exception e) {
			super.writer("error"+e.getMessage());
		}
		return null;
	}
	public ShareRole getRole() {
		return role;
	}
	public void setRole(ShareRole role) {
		this.role = role;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
}
