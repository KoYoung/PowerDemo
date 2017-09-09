package org.powerSystem.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeptUtil {
	private Integer id;
	private String text;
	private Integer deptParent;
	private Map attributes = new HashMap();
	private List<DeptUtil> children = new ArrayList<DeptUtil>();
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getDeptParent() {
		return deptParent;
	}
	public void setDeptParent(Integer deptParent) {
		this.deptParent = deptParent;
	}
	public List<DeptUtil> getChildren() {
		return children;
	}
	public void setChildren(List<DeptUtil> children) {
		this.children = children;
	}
	public Map getAttributes() {
		return attributes;
	}
	public void setAttributes(Map attributes) {
		this.attributes = attributes;
	}
	

}
