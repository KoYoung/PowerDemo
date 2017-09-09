package org.powerSystem.web.mem.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.powerSystem.entity.mem.MemCard;
import org.powerSystem.entity.mem.MemGift;
import org.powerSystem.service.mem.MemCardService;
import org.powerSystem.service.mem.MemGiftService;
import org.powerSystem.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace("/mem")
public class MemGiftAction extends BaseAction {
	private MemGift memGift;
	@Autowired
	private MemGiftService memGiftBiz;
	private String ids;
	private File pic;
	private String picFileName;
	private Integer jiFen;
	private String cardno;
	@Autowired
	private MemCardService memCardService;

	/**
	 * 在卡里面双击显示所有
	 */
	@Action("queryMemGift")
	public String queryMemGift() {
		System.out.println(jiFen+"=======jifen=========");

		try {
			Map map= new HashMap();
	
			if(jiFen>=0 && cardno!=null){
				map.put("giftIntegral<=",jiFen);
				String str = memGiftBiz.getDeptJson(map);
				getResponse().getWriter().write(str);
			
			}
			else{
			String str = memGiftBiz.getDeptJson(null);
			getResponse().getWriter().write(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * 更新礼品兑换信息
	 * 
	 * @return
	 */
	@Action("updateGiftCard")
	public String updateGiftCart() {
	
		System.out.println(memGift.getGiftId() + "==========gid=============");
		MemGift m = memGiftBiz.get(memGift.getGiftId());// 根据id获取一个对象
		System.out.println(cardno);
		m.setDealMark(m.getDealMark() - 1);// 数量减1
		//m.setGiftIntegral(jiFen - memGift.getDealMark());// 卡内积分减去礼物积分
		MemCard memCard=memCardService.getMemCard(cardno);
		System.out.println(memCard);
		System.out.println(memCard.getCardScore());
		System.out.println(m.getGiftIntegral());
		Integer cafen=memCard.getCardScore();
		Integer giftfen=m.getGiftIntegral();
		if(cafen-giftfen<0){
			writeJson("sfjsdlfj");
		}else{
			Integer aaa=cafen-giftfen;
			memCard.setCardScore(aaa);
			memCardService.updateMemCard(memCard);
			memGiftBiz.updateGift(m);
			writeJson("success");
		}
		// 更新卡信息
		return null;

	}
	/**
	 * 在礼品页面查看全部
	 * @return
	 */
	@Action("queryMemGiftt")
		public  String queryMemGiftt()
		{
			String str=memGiftBiz.getDeptJson(null);
			try {
				getResponse().getWriter().write(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	/**
	 * 修改
	 * 
	 * @return
	 */
	@Action("updateGift")
	public String updateGift() {
			memGiftBiz.updateGift(memGift);
			return "updateGift";
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@Action("savaMemGift")
	public String savaMemGift() {
		try {
			String realpath = ServletActionContext.getServletContext()
					.getRealPath("/files");
			ServletActionContext.getRequest()
					.setAttribute("realpath", realpath);
			
			if (pic != null) {
				File savedir = new File(realpath);
				String suffix = picFileName.substring(picFileName.lastIndexOf("."));
				String picname = realpath + "/" + new Date().getTime()+suffix;
				File savefile = new File(picname);
				FileUtils.copyFile(pic, savefile);
				memGift.setGiftPic(picname);
				// 把路径set到memGift对象
				ActionContext.getContext().put("message", "上传成功");
			}
			memGiftBiz.savaMemGift(memGift);
			writer("success");
		} catch (Exception e) {
			e.printStackTrace();
			writer("失败");
		}
		return null;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Action("delMemGift")
	public String delMemGift()
	{
		memGiftBiz.delMemGift(ids);
		writer("success");
		return null;
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	@Action("updateMemGift")
	public String updateMemGift() {
		memGiftBiz.updateMemGift(memGift);
		writeJson("success");
		return null;
	}

	public MemGift getMemGift() {
		return memGift;
	}

	public void setMemGift(MemGift memGift) {
		this.memGift = memGift;
	}

	public MemGiftService getMemGiftBiz() {
		return memGiftBiz;
	}

	public void setMemGiftBiz(MemGiftService memGiftBiz) {
		this.memGiftBiz = memGiftBiz;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public Integer getJiFen() {
		return jiFen;
	}

	public void setJiFen(Integer jiFen) {
		this.jiFen = jiFen;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public MemCardService getMemCardService() {
		return memCardService;
	}
	public void setMemCardService(MemCardService memCardService) {
		this.memCardService = memCardService;
	}

	
}
