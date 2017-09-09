package org.powerSystem.web.role.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.powerSystem.entity.ShareAdmin;
import org.powerSystem.service.role.ShareAdminService;
import org.powerSystem.util.PinyingUtil;
import org.powerSystem.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("struts-default")
@Result(name="input",location="/error.jsp")
public class ShareAdminAction extends BaseAction {
	private ShareAdmin admin;
	private String ids;
	@Autowired
	private ShareAdminService adminService;
	@Action("getMenus")
	public String loginUser() {
		try {
			String str = adminService.queryMenus(super.getSessionUser());
			super.writer(str);
		} catch (Exception e) {
			e.printStackTrace();
			super.writer("null");
		}
		return null;
	}

	/**
	 * 登陆
	 * 
	 * @return
	 */
	@Action("checkUser")
	public String checkUser() {
		try {
			ShareAdmin a = adminService.queryLogin(admin.getAdminName(),
					admin.getAdminPassword());
			super.setSessionUser(a);
			super.writer("success");
		} catch (Exception e) {
			super.writer(e.getMessage());
		}
		return null;
	}

	@Action("getAdminName")
	public String getAdminName() {
		String adminname = super.getRequest().getParameter("adminName");
		if (adminname != null && !"".equals(adminname.trim())) {
			String name = PinyingUtil.hanziToPinyin(adminname, ""); // 调用汉字转拼音
			try {
				adminService.queryAdminCheckName(name);
				super.writer("{\"name\":\"" + name + "\",\"value\":\"\"}");
			} catch (Exception e) {
				super.writer("{\"name\":\"" + name + "\",\"value\":\""
						+ e.getMessage() + "\"}");
			}
		} else {
			super.writer("{\"name\":\"\",\"value\":\"登陆名不能为空\"}");
		}
		return null;
	}
	@Action(value="loginout",results={@Result(name="success",location="/main.jsp",type="redirect")})
	public String loginout() {
		super.getSession().invalidate();
		return SUCCESS;
	}
	@Action("getRolesByempid")
	public String getAdminRoles(){
		super.writer(adminService.queryAdminRoles(admin.getAdminName()));
		return null;
	}
	@Action("allotmentRoles")
	public String allotmentRoles(){
		try {
			adminService.update(admin,ids);
			super.writer("success");
		} catch (Exception e) {
			super.writer("角色配置错误"+e.getMessage());
		}
		
		return null;
	}
	
	@Action("editUserpass")
	public String editUserpass(){
		String newpass = ServletActionContext.getRequest().getParameter("newpassword");
		if(super.getSessionUser()!=null && super.getSessionUser().getAdminPassword().equals(admin.getAdminPassword())){
			adminService.updatePass(super.getSessionUser().getAdminName(), newpass);
			super.writer("success");
		}else{
			super.writer("密码修改失败，原密码输入错误");
		}
		return null;
	}
	
	
	
	public ShareAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(ShareAdminService adminService) {
		this.adminService = adminService;
	}

	public ShareAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(ShareAdmin admin) {
		this.admin = admin;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
