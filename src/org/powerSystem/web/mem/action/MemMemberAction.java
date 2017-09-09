package org.powerSystem.web.mem.action;

import java.io.IOException;
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
import org.powerSystem.entity.ShareAdmin;
import org.powerSystem.entity.mem.MemCard;
import org.powerSystem.entity.mem.MemMember;
import org.powerSystem.service.ShareDataBaseServiceImpl;
import org.powerSystem.service.mem.MemCardService;
import org.powerSystem.service.mem.MemMemberService;
import org.powerSystem.util.DataDictionary;
import org.powerSystem.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace("/mem")
public class MemMemberAction extends BaseAction {
	private MemMember member;
	private MemCard card;
	@Autowired
	private MemMemberService memberService;
	@Autowired
	private MemCardService cardservice;
	private ShareAdmin shareAdmin;
	private String ids;
	private String memName;
	private MemCardService memCardService;
	
	
	
	
	/**
	 * 根据姓名查询全部
	 * @return
	 */
	@Action("queryAllByName")
	public String queryAllByName() {
		super.writer(memberService.query(member,getPage(),getRows()));
		return null;
	}
	
	
	/**
	 * 分页查询
	 * @return
	 */
	@Action("queryAllMem")
	public String queryAllMem(){
		Map<String, Object> map = new HashMap<String, Object>();
		if(member!=null){
			if (member.getMemName()!= null) {
				map.put("memName", "%" + member.getMemName()+ "%");
				String stream=memberService.getPage(map, getPage(), getRows());
				writeJson(stream);
			}}else{
		String stream=memberService.getPage(null, getPage(), getRows());
		writeJson(stream);//输出json类型的数据
		}
		return null;
	}
	
	
	
	/**
	 * 添加会员
	 * @return
	 */
	@Action("addMember")
	public String addMember(){
		try {
			memberService.saveMember(member);
			writer("success");
		} catch (Exception e) {
			writer("失败");
		}
		return null;
	}
	
	
	
	/**
	 * 根据id查询
	 * 会员显示
	 */
	public String queryMemberById(){
//		Map map=new HashMap();
//		map.put(member,member.getMemId());
//		List list=memberService.queryMemberById(member.getMemId());
//		ServletActionContext.getRequest().setAttribute("member",list);
//		List list2=memberService.queryMemberAll();
//		ServletActionContext.getRequest().setAttribute("list2",list2);
//		return "success1";
		member=memberService.queryMemberById(member.getMemId());
		ServletActionContext.getRequest().setAttribute("member",member);
		List list2=memberService.queryMemberAll();
		ServletActionContext.getRequest().setAttribute("list2",list2);
		return "queryMemberByid";
	}
	
	/**
	 * 删除会员
	 */
	@Action("deleteMember")
	public String deleteMember(){
		memberService.delMember(ids);
		writer("success");
		return null;
	}
	
	
	/**
	 * 卡类型未加载
	 * 卡等级未加载
	 * 性别未加载
	 * @return
	 */
	@Action("getTypeSexGrade")
	public String getTypeSexGrade(){
		String str =memberService.getCombobox();
		try {
			getResponse().getWriter().write(str);
		} catch (IOException e) {
		}
		return null;
	}
	
	
	
	@Action("getMembers")
	public String getMembers(){
		List list=memberService.queryMemberAll();
		ServletActionContext.getRequest().setAttribute("list",list);
		return "queryMember";
	}
	
	
	public MemMember getMember() {
		return member;
	}
	public void setMember(MemMember member) {
		this.member = member;
	}
	public MemCard getCard() {
		return card;
	}
	public void setCard(MemCard card) {
		this.card = card;
	}
	public MemMemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemMemberService memberService) {
		this.memberService = memberService;
	}
	public MemCardService getCardservice() {
		return cardservice;
	}
	public void setCardservice(MemCardService cardservice) {
		this.cardservice = cardservice;
	}
	public ShareAdmin getShareAdmin() {
		return shareAdmin;
	}
	public void setShareAdmin(ShareAdmin shareAdmin) {
		this.shareAdmin = shareAdmin;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	
	
}
