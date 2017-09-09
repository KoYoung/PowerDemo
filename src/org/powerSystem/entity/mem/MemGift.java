package org.powerSystem.entity.mem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MemGift entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mem_gift")
public class MemGift implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer giftId;
	private String giftName;
	private Integer dealMark;
	private String giftPic;
	private Integer giftIntegral;

	// Constructors

	/** default constructor */
	public MemGift() {
	}

	/** full constructor */
	public MemGift(String giftName, Integer dealMark, String giftPic,Integer giftIntegral) {
		this.giftName = giftName;
		this.dealMark = dealMark;
		this.giftPic = giftPic;
		this.giftIntegral=giftIntegral;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "gift_id", unique = true, nullable = false)
	public Integer getGiftId() {
		return this.giftId;
	}

	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}

	@Column(name = "gift_Name", length = 50)
	public String getGiftName() {
		return this.giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	@Column(name = "deal_mark")
	public Integer getDealMark() {
		return this.dealMark;
	}

	public void setDealMark(Integer dealMark) {
		this.dealMark = dealMark;
	}

	@Column(name = "gift_pic", length = 300)
	public String getGiftPic() {
		return this.giftPic;
	}

	public void setGiftPic(String giftPic) {
		this.giftPic = giftPic;
	}

	public Integer getGiftIntegral() {
		return giftIntegral;
	}

	public void setGiftIntegral(Integer giftIntegral) {
		this.giftIntegral = giftIntegral;
	}

}