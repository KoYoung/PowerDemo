package org.powerSystem.entity.mem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MemActivitydetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mem_activitydetail")
public class MemActivitydetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer actDetailId;
	private Integer goodsId;
	private Float discount;
	private Integer actId;
//	private Integer actType;

	// Constructors

	/** default constructor */
	public MemActivitydetail() {
	}

	/** full constructor */
	public MemActivitydetail(Integer goodsId, Float discount, Integer actId,Integer actType) {
		this.goodsId = goodsId;
		this.discount = discount;
		this.actId = actId;
	//	this.actType=actType;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "act_detail_id", unique = true, nullable = false)
	public Integer getActDetailId() {
		return this.actDetailId;
	}

	public void setActDetailId(Integer actDetailId) {
		this.actDetailId = actDetailId;
	}

	@Column(name = "goods_Id")
	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "discount", precision = 12, scale = 0)
	public Float getDiscount() {
		return this.discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	@Column(name = "act_id")
	public Integer getActId() {
		return this.actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}
	/*
	@Column(name="act_type")
	public Integer getActType() {
		return actType;
	}

	public void setActType(Integer actType) {
		this.actType = actType;
	}
	*/
}