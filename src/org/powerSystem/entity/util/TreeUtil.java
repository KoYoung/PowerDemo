package org.powerSystem.entity.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtil {

	private String id;
	private String text;
	private String state;
	private String checked;
	private String iconCls;
	private List<TreeUtil> children = new ArrayList<TreeUtil>(0);
	private Map<String,String> attributes = new HashMap<String, String>(0);
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		if(state==null)return "open";
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getChecked() {
		if(checked==null)return "false";
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public List<TreeUtil> getChildren() {
		return children;
	}
	public void setChildren(List<TreeUtil> children) {
		this.children = children;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
