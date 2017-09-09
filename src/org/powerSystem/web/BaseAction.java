package org.powerSystem.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.powerSystem.entity.ShareAdmin;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	
	private int rows;
	private int page;
	public int getRows() {
		return rows==0?10:rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		if(page<=0)page=1;
		return (page-1)*getRows();
	}
	public void setPage(int page) {
		this.page = page;
	}
	//输出json类型的数据
	@Deprecated
	public void writeJson(String o){
		try {
			getResponse().getWriter().write(o);
		} catch (Exception e) {
		}
	}
	
	public HttpServletRequest getRequest(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		return request;
	}	
	public HttpServletResponse getResponse(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		return response;
	}
	
	public HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	public ShareAdmin getSessionUser(){
		ShareAdmin admin =  (ShareAdmin) getSession().getAttribute("user");
		return admin;
	}
	public void setSessionUser(ShareAdmin admin){
		getSession().setAttribute("user", admin);
	}
	
	public void writer(String msg) {
		try {
			getResponse().getWriter().print(msg);
		} catch (IOException e) {
		} 
	}
}
