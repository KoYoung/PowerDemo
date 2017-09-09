package org.powerSystem.entity.mem;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.engine.Cascade;

/**
 * MemMember entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mem_member")
public class MemMember implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer memId;
	private String memName;
	private Integer memSex;
	private Integer memAge;
	private Date memBirthday;
	private String memAddress;
	private String memQq;
	private String memTel;
	private String memEmail;
	private Integer memStatus;
	private String memPic;
	private String handlerName;
	private Set<MemCard> memCards = new HashSet<MemCard>(0);

	// Constructors

	/** default constructor */
	public MemMember() {
	}

	/** full constructor */
	public MemMember(String memName, Integer memAge, Date memBirthday,
			String memAddress, String memTel, String memQq, String memEmail,
			Integer memStatus, String handlerName, String memPic,
			Set<MemCard> memCards,Integer memSex) {
		this.memName = memName;
		this.memAge = memAge;
		this.memBirthday = memBirthday;
		this.memAddress = memAddress;
		this.memTel = memTel;
		this.memQq = memQq;
		this.memEmail = memEmail;
		this.memStatus = memStatus;
		this.handlerName = handlerName;
		this.memPic = memPic;
		this.memCards = memCards;
		this.memSex = memSex;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "mem_id", unique = true, nullable = false)
	public Integer getMemId() {
		return this.memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	@Column(name = "mem_name", length = 50)
	public String getMemName() {
		return this.memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	@Column(name = "mem_Age")
	public Integer getMemAge() {
		return this.memAge;
	}

	public void setMemAge(Integer memAge) {
		this.memAge = memAge;
	}

	@Column(name = "mem_Birthday", length = 0)
	public Date getMemBirthday() {
		return this.memBirthday;
	}

	public void setMemBirthday(Date memBirthday) {
		this.memBirthday = memBirthday;
	}

	@Column(name = "mem_Address", length = 50)
	public String getMemAddress() {
		return this.memAddress;
	}

	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}

	@Column(name = "mem_Tel", length = 50)
	public String getMemTel() {
		return this.memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

	@Column(name = "mem_Qq", length = 50)
	public String getMemQq() {
		return this.memQq;
	}

	public void setMemQq(String memQq) {
		this.memQq = memQq;
	}

	@Column(name = "mem_Email", length = 50)
	public String getMemEmail() {
		return this.memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	@Column(name = "mem_Status")
	public Integer getMemStatus() {
		return this.memStatus;
	}

	public void setMemStatus(Integer memStatus) {
		this.memStatus = memStatus;
	}

	@Column(name = "handlerName", length = 20)
	public String getHandlerName() {
		return this.handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	@Column(name = "mem_pic", length = 50)
	public String getMemPic() {
		return this.memPic;
	}

	public void setMemPic(String memPic) {
		this.memPic = memPic;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "memMember")
	public Set<MemCard> getMemCards() {
		return this.memCards;
	}

	public void setMemCards(Set<MemCard> memCards) {
		this.memCards = memCards;
	}
	@Column(name="mem_sex")
	public Integer getMemSex() {
		return memSex;
	}
	
	public void setMemSex(Integer memSex) {
		this.memSex = memSex;
	}


}