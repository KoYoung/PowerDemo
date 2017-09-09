package org.powerSystem.web.mem.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.powerSystem.entity.mem.MemRank;
import org.powerSystem.service.mem.MemRankService;
import org.powerSystem.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;




@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace("/mem")
public class MemRankAction extends BaseAction {
	private MemRank memRank;
	@Autowired
	private MemRankService memRankBiz; 
	private String ids;
	/**
	 * 查看所有规则
	 * @return
	 */

	@Action("queryMemRank")
	public String queryMemRank() 
		{
		String str=memRankBiz.getDeptJson();
		try {
			getResponse().getWriter().write(str);
		} catch (IOException e) {
		}
		//writeJson(str);
		return null;
		}
/**
 * 添加规则
 * @return
 */
@Action("addMemRank")
	public String addMemRank(){
	try {
		memRankBiz.savaMemRank(memRank);
		writer("success");
	} catch (Exception e) {
		writer("失败");
	}
	return null;
}
/**
 * 保存
 * @return
 */
	public String saveMemRank()
	{
		memRankBiz.savaMemRank(memRank);
		return "savaMemRank";
	}
	/**
	 * 删除
	 */
	@Action("deleteMemRak")
	public String deleteMemRak()
	{
		memRankBiz.delMemRank(ids);
		writer("success");
		return null;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@Action("updateRank")
	public String updateRank()
	{
		memRankBiz.updateRank(memRank);
		return "updateRank";
	}
	/**
	 * 更新
	 * @return
	 */
	@Action("updateMemRank")
	public String updateMemRank()
	{
		memRankBiz.updateMemRank(memRank);
		writeJson("success");
		return null;
	}
	public MemRank getMemRank() {
		return memRank;
	}
	public void setMemRank(MemRank memRank) {
		this.memRank = memRank;
	}
	public MemRankService getMemRankBiz() {
		return memRankBiz;
	}
	public void setMemRankBiz(MemRankService memRankBiz) {
		this.memRankBiz = memRankBiz;
	}
	public MemRankAction(MemRank memRank, MemRankService memRankBiz) {
		super();
		this.memRank = memRank;
		this.memRankBiz = memRankBiz;
	}
	public MemRankAction() {
		super();
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	
	
}
