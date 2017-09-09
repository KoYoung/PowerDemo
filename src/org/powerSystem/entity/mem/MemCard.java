package org.powerSystem.entity.mem;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MemCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mem_card")
public class MemCard implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private String cardNo;// 卡号 主键
	private MemMember memMember;
	private Integer cardLevel;//卡等级
	private Integer cardType;//卡类型
	private Integer cardRemark;//卡备注
	private Date createTime;//办卡时间
	private Integer cardStatus;//卡状态
	private Float cardMoney;//卡内金额
	private Integer cardScore;//卡积分
	private Integer deptId;//部门号
	private String cardPass;//卡密码
	private Date cardIndate;//卡到期时间


	public MemCard() {
	}

	public MemCard(MemMember memMember, Integer cardLevel, Integer cardType,
			Integer cardRemark, Date createTime, Integer cardStatus,
			Float cardMoney, Integer cardScore, Integer deptId, String cardPass,Date cardIndate) {
		this.memMember = memMember;
		this.cardLevel = cardLevel;
		this.cardType = cardType;
		this.cardRemark = cardRemark;
		this.createTime = createTime;
		this.cardStatus = cardStatus;
		this.cardMoney = cardMoney;
		this.cardScore = cardScore;
		this.deptId = deptId;
		this.cardPass = cardPass;
		this.cardIndate=cardIndate;
	}

	@Id
	/*@GeneratedValue*/
	@Column(name = "card_no", unique = true, nullable = false, length = 9)
	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mem_id")
	public MemMember getMemMember() {
		return this.memMember;
	}

	public void setMemMember(MemMember memMember) {
		this.memMember = memMember;
	}

	@Column(name = "card_Level")
	public Integer getCardLevel() {
		return this.cardLevel;
	}

	public void setCardLevel(Integer cardLevel) {
		this.cardLevel = cardLevel;
	}

	@Column(name = "card_Type")
	public Integer getCardType() {
		return this.cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	@Column(name = "card_Remark")
	public Integer getCardRemark() {
		return this.cardRemark;
	}

	public void setCardRemark(Integer cardRemark) {
		this.cardRemark = cardRemark;
	}

	@Column(name = "create_Time", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "card_Status")
	public Integer getCardStatus() {
		return this.cardStatus;
	}

	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}

	@Column(name = "card_Money", precision = 12, scale = 0)
	public Float getCardMoney() {
		return this.cardMoney;
	}

	public void setCardMoney(Float cardMoney) {
		this.cardMoney = cardMoney;
	}

	@Column(name = "card_score")
	public Integer getCardScore() {
		return this.cardScore;
	}

	public void setCardScore(Integer cardScore) {
		this.cardScore = cardScore;
	}

	@Column(name = "dept_id")
	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Column(name = "card_pass", length = 20)
	public String getCardPass() {
		return this.cardPass;
	}

	public void setCardPass(String cardPass) {
		this.cardPass = cardPass;
	}
	@Column(name = "card_indate")
	public Date getCardIndate() {
		return cardIndate;
	}

	public void setCardIndate(Date cardIndate) {
		this.cardIndate = cardIndate;
	}

	@Override
	public String toString() {
		return "MemCard [cardNo=" + cardNo + ", memMember=" + memMember
				+ ", cardLevel=" + cardLevel + ", cardType=" + cardType
				+ ", cardRemark=" + cardRemark + ", createTime=" + createTime
				+ ", cardStatus=" + cardStatus + ", cardMoney=" + cardMoney
				+ ", cardScore=" + cardScore + ", deptId=" + deptId
				+ ", cardPass=" + cardPass + ", cardIndate=" + cardIndate + "]";
	}
	
	
	

}