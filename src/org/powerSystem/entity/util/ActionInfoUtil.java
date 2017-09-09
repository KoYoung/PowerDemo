package org.powerSystem.entity.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.powerSystem.entity.ShareActioninfo;


public class ActionInfoUtil {
	private Integer actionId;
	private String actionname;
	private Integer actionParent;//null 最顶的
	private String actionPic;
	private String actionUrl;
	private Integer actionSort;
	private String parentName;
	private List<ActionInfoUtil> children = new ArrayList<ActionInfoUtil>();
	public ActionInfoUtil(){}
	public ActionInfoUtil(Integer actionId, String actionname,
			Integer actionParent, String actionPic, String actionUrl,
			Integer actionSort) {
		super();
		this.actionId = actionId;
		this.actionname = actionname;
		this.actionParent = actionParent;
		this.actionPic = actionPic;
		this.actionUrl = actionUrl;
		this.actionSort = actionSort;
	}
	
	
	public void setActionInfo(ShareActioninfo actioninfo){
		this.actionId = actioninfo.getActionId();
		this.actionname = actioninfo.getActionname();
		this.actionParent = actioninfo.getActionParent();
		this.actionPic = actioninfo.getActionPic();
		this.actionUrl = actioninfo.getActionUrl();
		this.actionSort = actioninfo.getActionSort();
	}
	
	public Integer getActionId() {
		return actionId;
	}
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}
	public String getActionname() {
		return actionname;
	}
	public void setActionname(String actionname) {
		this.actionname = actionname;
	}
	public Integer getActionParent() {
		return actionParent;
	}
	public void setActionParent(Integer actionParent) {
		this.actionParent = actionParent;
	}
	public String getActionPic() {
		return actionPic;
	}
	public void setActionPic(String actionPic) {
		this.actionPic = actionPic;
	}
	public String getActionUrl() {
		return actionUrl;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	public Integer getActionSort() {
		return actionSort;
	}
	public void setActionSort(Integer actionSort) {
		this.actionSort = actionSort;
	}
	public List<ActionInfoUtil> getChildren() {
		return children;
	}
	public void setChildren(List<ActionInfoUtil> children) {
		this.children = children;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
