package org.powerSystem.web.mem.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.powerSystem.entity.mem.MemScoreRule;
import org.powerSystem.service.mem.MemScoreRuleService;
import org.powerSystem.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace("/mem")
public class MemScoreRuleAction extends BaseAction {
	private MemScoreRule memScoreRule;
	@Autowired
	private MemScoreRuleService memScoreRuleBiz;
	private String ids;
	
	public MemScoreRule getMemScoreRule() {
		return memScoreRule;
	}
	public void setMemScoreRule(MemScoreRule memScoreRule) {
		this.memScoreRule = memScoreRule;
	}
	public MemScoreRuleService getMemScoreRuleBiz() {
		return memScoreRuleBiz;
	}
	public void setMemScoreRuleBiz(MemScoreRuleService memScoreRuleBiz) {
		memScoreRuleBiz = memScoreRuleBiz;
	}
	/**
	 * 查看全部
	 * @return
	 */
	@Action("getAll")
	public String getAll()
	{
		String str=memScoreRuleBiz.getDeptJson();
		try {
			getResponse().getWriter().write(str);
		} catch (IOException e) {
		}
		//writeJson(str);
		return null;
		}
	/**
	 * 添加
	 * @return
	 */
	@Action("addMemScoreRule")
	public String addMemScoreRule()
	{
		System.out.println();
		try{
		memScoreRuleBiz.savaMemScoreRule(memScoreRule);
	writer("success");
	}catch(Exception e)
	{
	//	writer("失败");
		e.printStackTrace();
	}
	return null;
	}
	/**
	 * 根据id查询
	 * @return
	 */
	@Action("queryMemScoreRule")
	public String queryMemScoreRule()
	{
		memScoreRule=memScoreRuleBiz.get(memScoreRule.getRuleId());
		ServletActionContext.getRequest().setAttribute("memScoreRule", memScoreRule);
		List lists=memScoreRuleBiz.getAll();
		ServletActionContext.getRequest().setAttribute("lists", lists);
		return "queryMemScoreRule";
	}
/**
 * 删除
 * @return
 */
	@Action("deleteMemScoreRak")
	public String deleteMemScoreRak()
	{
		memScoreRuleBiz.delMemScoreRank(ids);
		writer("success");
		return null;
	}
	/**
	 * 修改
	 * @return
	 */
	@Action("updateScoreRule")
	public String updateScoreRule()
	{
		memScoreRuleBiz.updateScoreRule(memScoreRule);
		return "memScoreRule";
	}
	/**
	 * 更新
	 * @return
	 */
	@Action("updateMSR")
	public String updateMSR()
	{
		memScoreRuleBiz.updateMemSr(memScoreRule);
		writeJson("success");
		return null;
	}
public String getIds() {
	return ids;
}
public void setIds(String ids) {
	this.ids = ids;
}

}
