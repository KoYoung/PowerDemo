package org.powerSystem.entity.mem;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MemActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mem_activity")
public class MemActivity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer actId;
	private String actName;
	private Date actBeginTime;
	private Date actEndTime;
	private String actRemark;

	// Constructors

	/** default constructor */
	public MemActivity() {
	}

	/** full constructor */
	public MemActivity(String actName, Date actBeginTime,
			Date actEndTime, String actRemark) {
		this.actName = actName;
		this.actBeginTime = actBeginTime;
		this.actEndTime = actEndTime;
		this.actRemark = actRemark;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "act_id", unique = true, nullable = false)
	public Integer getActId() {
		return this.actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	@Column(name = "act_name", length = 50)
	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	@Column(name = "act_beginTime", length = 0)
	public Date getActBeginTime() {
		return this.actBeginTime;
	}

	public void setActBeginTime(Date actBeginTime) {
		this.actBeginTime = actBeginTime;
	}

	@Column(name = "act_endTime", length = 0)
	public Date getActEndTime() {
		return this.actEndTime;
	}

	public void setActEndTime(Date actEndTime) {
		this.actEndTime = actEndTime;
	}

	@Column(name = "act_remark", length = 300)
	public String getActRemark() {
		return this.actRemark;
	}

	public void setActRemark(String actRemark) {
		this.actRemark = actRemark;
	}

}