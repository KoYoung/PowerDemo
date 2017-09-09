package org.powerSystem.entity.mem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MemOrderDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mem_order_detail")
public class MemOrderDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer detailId;
	private Integer detailNum;
	private Float detailPrice;
	private Float detailRebate;
	private Integer goodsId;
	private String orderNo;

	// Constructors

	/** default constructor */
	public MemOrderDetail() {
	}

	/** full constructor */
	public MemOrderDetail(Integer detailNum, Float detailPrice,
			Float detailRebate, Integer goodsId) {
		this.detailNum = detailNum;
		this.detailPrice = detailPrice;
		this.detailRebate = detailRebate;
		this.goodsId = goodsId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "detail_id", unique = true, nullable = false)
	public Integer getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	@Column(name = "detail_num")
	public Integer getDetailNum() {
		return this.detailNum;
	}

	public void setDetailNum(Integer detailNum) {
		this.detailNum = detailNum;
	}

	@Column(name = "detail_price", precision = 12, scale = 0)
	public Float getDetailPrice() {
		return this.detailPrice;
	}

	public void setDetailPrice(Float detailPrice) {
		this.detailPrice = detailPrice;
	}

	@Column(name = "detail_rebate", precision = 12, scale = 0)
	public Float getDetailRebate() {
		return this.detailRebate;
	}

	public void setDetailRebate(Float detailRebate) {
		this.detailRebate = detailRebate;
	}

	@Column(name = "goods_id")
	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	@Column(name = "order_no")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}