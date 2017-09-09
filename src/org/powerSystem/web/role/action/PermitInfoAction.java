package org.powerSystem.web.role.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.powerSystem.entity.SharePermitinfo;
import org.powerSystem.service.role.PermitInfoService;
import org.powerSystem.service.role.ShareActioninfoService;
import org.powerSystem.util.DataDictionary;
import org.powerSystem.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("struts-default")
@Namespace("/role")
public class PermitInfoAction extends BaseAction {

	private String ids;
	private SharePermitinfo permit;
	@Autowired
	private PermitInfoService permitService;

	
	@Action("getPermitInfos")
	public String getPermitInfos() {
		super.writer(permitService.query());
		return null;
	}
	@Action("getPermitInfosByid")
	public String getMenusInfo(){
		try {
			String info = permitService.query(permit.getPerId());
			super.writer(info);
		} catch (Exception e) {
			super.writer("error"+e.getMessage());
		}
		return null;
	}
	@Action("addPermitInfo")
	public String addPermitInfo() {
		try {
			int index = permitService.savePermit(permit);
			super.writer("{\"msg\":\"success\",\"index\":" + index + "}");
		} catch (Exception e) {
			super.writer("{\"msg\":\"error\",\"index\":0}");
		}
		return null;
	}
	@Action("deletePermit")
	public String deletePermit() {
		if (permit != null) {
			permitService.deletePermit(permit.getPerId());
			super.writer("success");
		} else {
			super.writer("error");
		}
		return null;
	}
	@Action("allotmentAction")
	public String allotmentAction(){
		try {
			permitService.update(permit,ids);
			super.writer("success");
		} catch (Exception e) {
			super.writer("权限配置错误"+e.getMessage());
		}
		
		return null;
	}
	public SharePermitinfo getPermit() {
		return permit;
	}

	public void setPermit(SharePermitinfo permit) {
		this.permit = permit;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
}
