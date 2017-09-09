package org.powerSystem.web.role.action;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.powerSystem.entity.ShareActioninfo;
import org.powerSystem.service.role.ShareActioninfoService;
import org.powerSystem.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
@ParentPackage("struts-default")
@Namespace("/role")
public class ShareActionInfoAction extends BaseAction {
	private ShareActioninfo actioninfo;
	@Autowired
	private ShareActioninfoService actionService;
	@Action("getMenusInfo")
	public String getMenusInfo(){//查看菜单
		try {
			if(actioninfo!=null && actioninfo.getActionname()!=null){
				String info = actionService.query(actioninfo.getActionname());
				getResponse().getWriter().write(info);
			}else{
				String info = actionService.query();
				getResponse().getWriter().write(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Action("addMenusinfo")
	public String addMenusinfo(){//添加菜单
		try {
			actionService.saveActions(actioninfo);
			super.writer("success");
		} catch (Exception e) {
			super.writer(e.getMessage());
		}
		return null;
	}
	@Action("deleteMenuinfo")
	public String deleteMenuinfo(){//删除菜单
		try {
			if(actioninfo!=null && actioninfo.getActionId()!=null){
				actionService.deleteActions(actioninfo.getActionId());
				super.writer("success");
			}
		} catch (Exception e) {
			super.writer(e.getMessage());
		}
		
		return null;
	}
	
	
	public ShareActioninfo getActioninfo() {
		return actioninfo;
	}
	public void setActioninfo(ShareActioninfo actioninfo) {
		this.actioninfo = actioninfo;
	}
}
