package org.powerSystem.entity.mem;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * MemOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mem_order")
public class MemOrder implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="seq_order")
	@SequenceGenerator(name="seq_order",sequenceName="seq_order")
	private String orderNo;
	private Date orderDate;
	private Float orderAllprice;
	private String orderRemark;
	private Integer orderIntegral;
	private String cardNo;

	// Constructors

	/** default constructor */
	public MemOrder() {
	}

	/** full constructor */
	public MemOrder(Timestamp orderDate, Float orderAllprice,
			String orderRemark, Integer orderIntegral, String cardNo) {
		this.orderDate = orderDate;
		this.orderAllprice = orderAllprice;
		this.orderRemark = orderRemark;
		this.orderIntegral = orderIntegral;
		this.cardNo = cardNo;
	}

	// Property accessors
	@Id
	//@GeneratedValue
	@Column(name = "order_no", unique = true, nullable = false, length = 20)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "order_date", length = 0)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "order_Allprice", precision = 12, scale = 0)
	public Float getOrderAllprice() {
		return this.orderAllprice;
	}

	public void setOrderAllprice(Float orderAllprice) {
		this.orderAllprice = orderAllprice;
	}

	@Column(name = "order_remark", length = 100)
	public String getOrderRemark() {
		return this.orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	@Column(name = "order_integral")
	public Integer getOrderIntegral() {
		return this.orderIntegral;
	}

	public void setOrderIntegral(Integer orderIntegral) {
		this.orderIntegral = orderIntegral;
	}

	@Column(name = "card_no", length = 20)
	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

}