package org.powerSystem.web.mem.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.powerSystem.entity.mem.MemCard;
import org.powerSystem.entity.mem.MemOrder;
import org.powerSystem.entity.mem.MemOrderDetail;
import org.powerSystem.entity.mem.MemRank;
import org.powerSystem.service.mem.MemCardService;
import org.powerSystem.service.mem.MemOrderService;
import org.powerSystem.service.mem.MemRankService;
import org.powerSystem.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpResponse;

@ParentPackage(value="struts-default")

public class MemOrderAction extends BaseAction {
	private MemOrder memOrder;
	private MemOrderDetail memOrderDetail;
	@Autowired
	private MemOrderService memOrderService;
	@Autowired
	private MemCardService memCardServiceImpl;
	@Autowired
	private MemRankService memRankServiceImpl;
	private static final long serialVersionUID = 1L;
	private static final String OrderNo = null;
	private MemCard memcard;
	private String ids;
	private Integer orderIntegral;
	private String cardNo;
	private Integer id;
	private MemRank memRank;
	@Autowired
	private MemCardService memCardService;
	private MemCard memCard;
	public Integer getOrderIntegral() {
		return orderIntegral;
	}
	public void setOrderIntegral(Integer orderIntegral) {
		this.orderIntegral = orderIntegral;
	}
	/**
	 * 查询订单
	 */
	@Action("queryAllOrder")
	public String queryAllOrder(){
		Map<String, Object> map = new HashMap<String, Object>();
		if(memOrder!=null){
			if (memOrder.getCardNo()!= null) {
				map.put("cardNo", "%" + memOrder.getCardNo()+ "%");
				String stream=memOrderService.getPage(map, getPage(), getRows());
				writeJson(stream);
			}}else{
		String stream=memOrderService.getPage(null, getPage(), getRows());
		writeJson(stream);//输出json类型的数据
		}
		return null;
		
//		String orders=memOrderService.getPage(null, getPage(), getRows());
//		try {
//			getResponse().getWriter().write(orders);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(orders);
//		return null;
	}
	/**
	 * 	public String getPage(Map map, int startIndex, int pageSize) {
		PageUtil pages=new PageUtil();
		pages.setRows(findDepts(map, startIndex, pageSize));
		pages.setTotal(getCount(map));
			JsonConfig con =JsonConfigUtil.getJsonConfigInstanceByFormatDate();
			con.setExcludes(new String[] {"employees","orderses","rooms","employees"});
			String str = JSONObject.fromObject(pages,con).toString();
		    return str;
	}
	 */
	@Action("jisuan")
	public String jisuan(){
		String cardid=memcard.getCardNo();
		System.out.println(cardid+"==cardid");
		MemCard m=memCardServiceImpl.getMemCard(cardid);
		Integer id=m.getCardLevel();
		MemRank mk= memRankServiceImpl.getRank(id);
		String dis=mk.getDiscount().toString();
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("dis", dis);
		writeJson(dis);
		return null;
	}
	/**
	 * 根据id查询
	 * 会员卡显示
	 */
	public String queryMemCardById(){
		MemOrder list=memOrderService.queryOrderById(cardNo);
		ServletActionContext.getRequest().setAttribute("memOrder",list);
		return "success";
	}
	@Action("getOrder")
	public String getOrder(){
		List list=memOrderService.queryOrderAll();
		ServletActionContext.getRequest().setAttribute("list",list);
		return "success";
	}
	
	
	/**
	 * 修改订单
	 * @return
	 */
	@Action("updateMemOrder")
	public String updateMemOrder() {
		memOrderService.updateMember(memOrder);
		try {
			getResponse().getWriter().write("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 添加订单
	 * @return
	 */
	@Action("addOrder")
	public String addOrder(){
		try {
			
			System.out.println(memOrder.getOrderIntegral());
			System.out.println(memOrder.getCardNo());
			System.out.println(memCardService.query(memOrder.getCardNo()).getCardScore());
			Integer jf=memCardService.query(memOrder.getCardNo()).getCardScore();
			Integer bh=memOrder.getOrderIntegral();
			System.out.println(jf);
			System.out.println(bh);
			jf+=bh;
			System.out.println(jf);
			memCardService.updatecardscore(memOrder.getCardNo(),jf);
			String i="dd"+new Date().getTime();
			memOrder.setOrderNo(i);
			System.out.println(memOrder.getOrderRemark()+"########");
			memOrderService.saveOrder(memOrder);
			writer("success");
		} catch (Exception e) {
			writer("失败");
		}
		return null;
	}
	
	/**
	 * 保存
	 */
	@Action("saveMemOrder")
	public String saveMemOrder(){
		memOrderService.saveOrder(memOrder);
		return "saveMemOrder";
	}
	
	
	/**
	 * 删除会员
	 */
	@Action("delOrder")
	public String delOrder(){
		System.out.println("aaaaaaa");
		memOrderService.delOrder(ids);
		writer("success");
		return null;
	}
	

	public MemOrder getMemOrder() {
		return memOrder;
	}
	public void setMemOrder(MemOrder memOrder) {
		this.memOrder = memOrder;
	}
	public MemOrderDetail getMemOrderDetail() {
		return memOrderDetail;
	}
	public void setMemOrderDetail(MemOrderDetail memOrderDetail) {
		this.memOrderDetail = memOrderDetail;
	}
	public MemOrderService getMemOrderService() {
		return memOrderService;
	}
	public void setMemOrderService(MemOrderService memOrderService) {
		this.memOrderService = memOrderService;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public static String getOrderno() {
		return OrderNo;
	}

	public MemCardService getMemCardServiceImpl() {
		return memCardServiceImpl;
	}
	public void setMemCardServiceImpl(MemCardService memCardServiceImpl) {
		this.memCardServiceImpl = memCardServiceImpl;
	}
	
	public MemRankService getMemRankServiceImpl() {
		return memRankServiceImpl;
	}
	public void setMemRankServiceImpl(MemRankService memRankServiceImpl) {
		this.memRankServiceImpl = memRankServiceImpl;
	}
	public MemCard getMemcard() {
		return memcard;
	}
	public void setMemcard(MemCard memcard) {
		this.memcard = memcard;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public MemRank getMemRank() {
		return memRank;
	}
	public void setMemRank(MemRank memRank) {
		this.memRank = memRank;
	}
	public MemCardService getMemCardService() {
		return memCardService;
	}
	public void setMemCardService(MemCardService memCardService) {
		this.memCardService = memCardService;
	}
	public MemCard getMemCard() {
		return memCard;
	}
	public void setMemCard(MemCard memCard) {
		this.memCard = memCard;
	}
	
	

}
