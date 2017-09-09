package org.powerSystem.web.mem.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.powerSystem.entity.mem.MemCard;
import org.powerSystem.entity.mem.MemMember;
import org.powerSystem.service.mem.MemCardService;
import org.powerSystem.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("struts-default")
@Namespace("/mem")
public class MemCardAction extends BaseAction {
	private MemCard memCard;
	private Integer memId ;
	@Autowired
	private MemCardService memCardService;
	private String ids;
	private MemMember member;
	
	
	/**
	 * 根据会员id查询卡信息
	 * @param memId
	 * 		会员id
	 */
	@Action("queryCardId")
	public String queryCardId(){
		try{
	    Map map=new HashMap();
		map.put("memMember.memId",memId);
		List list=memCardService.queryCardInfoById(map);
		JsonConfig config=new JsonConfig();
		config.setExcludes(new String[] {"memMember"});//屏蔽属性
		writeJson(JSONArray.fromObject(list,config).toString());//输出json类型的数据
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
			}
	
	
	/**
	 * 查询所有
	 * 卡信息
	 */
	@Action("queryCardAll")
	public String queryCardAll(){
		List list=memCardService.queryMemCardAll();
		ServletActionContext.getRequest().setAttribute("list",list);
		return null;
	}
	
	
	/**
	 * 分页查询
	 * @return
	 */
	@Action("queryAllMemCard")
	public String queryAllMemCard(){
		String stream=memCardService.getPage(null,getPage(),getRows());
		System.out.println(stream);
		writeJson(stream);//输出json类型的数据
		return null;
	}
	
	
	
	/**
	 * 添加会员卡
	 * @return
	 */
	@Action("addMemberCard")
	public String addMemberCard(){
		
		try {
			memCard.setCreateTime(new Date());
			memCard.setCardStatus(1);
			memCard.setCardScore(0);
			memCard.setCardNo(memCardService.getcardno());
			memCardService.addMemCard(memCard);
			writer("success");
		} catch (Exception e) {
			writer("失败");
		}
		return null;
	}
	
	
	/**
	 * 
	 * 更新
	 * 会员卡信息
	 */
	@Action(value="updateCard",results={@Result(name="updateCard",location="/mem/updateMemCard.jsp",type="dispatcher"),
			@Result(name="error",location="/error.jsp")})
	public String updateCard()
	{
		memCard=memCardService.query(memCard.getCardNo()+"");
		ServletActionContext.getRequest().setAttribute("memCard",memCard);
		return "updateCard";
	}
	@Action(value="update",results={@Result(name="showCard",location="/mem/queryCardAll.action",type="redirect"),
			@Result(name="error",location="/error.jsp")})
	public String update(){
		memCardService.updateMemCard(memCard);
		String stream=memCardService.getPage(null,getPage(),getRows());
		System.out.println(stream);
		writeJson(stream);//输出json类型的数据
		return "showCard";
	}
	
	/**
	 * 根据id查询
	 * 会员卡显示
	 */
	public String queryMemCardById(){
		memCard=memCardService.query(memCard.getCardNo()+"");
		ServletActionContext.getRequest().setAttribute("member",memCard);//放入request中
		List list2=memCardService.queryMemCardAll();
		ServletActionContext.getRequest().setAttribute("list2",list2);
		return "queryMemberByid";
	}
	
	/**
	 * 保存
	 */
	@Action("saveMemCard")
	public String saveMemCard(){
		System.out.println("ss");
		memCardService.addMemCard(memCard);
		return "showCard";
	}
	/**
	 * 删除会员卡
	 */
	@Action("deleteMemberCard")
	public String deleteMemberCard(){
		memCardService.delMemCard(ids);
		System.out.println(ids);
		writer("success");
		return null;
	}

	

	public MemCard getMemCard() {
		return memCard;
	}
	public void setMemCard(MemCard memCard) {
		this.memCard = memCard;
	}
	public MemCardService getMemCardService() {
		return memCardService;
	}
	public void setMemCardService(MemCardService memCardService) {
		this.memCardService = memCardService;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	
}
