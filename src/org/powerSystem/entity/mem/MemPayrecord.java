package org.powerSystem.entity.mem;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MemPayrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mem_payrecord")
public class MemPayrecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer payId;
	private Timestamp payTime;
	private Float payMoney;
	private String cardNo;
	private Integer score;

	// Constructors

	/** default constructor */
	public MemPayrecord() {
	}

	/** full constructor */
	public MemPayrecord(Timestamp payTime, Float payMoney, String cardNo,
			Integer score) {
		this.payTime = payTime;
		this.payMoney = payMoney;
		this.cardNo = cardNo;
		this.score = score;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pay_Id", unique = true, nullable = false)
	public Integer getPayId() {
		return this.payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	@Column(name = "pay_time", length = 0)
	public Timestamp getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	@Column(name = "pay_money", precision = 12, scale = 0)
	public Float getPayMoney() {
		return this.payMoney;
	}

	public void setPayMoney(Float payMoney) {
		this.payMoney = payMoney;
	}

	@Column(name = "card_no", length = 9)
	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(name = "score")
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}