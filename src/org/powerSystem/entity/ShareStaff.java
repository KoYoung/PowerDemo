package org.powerSystem.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ShareStaff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "share_staff")
public class ShareStaff implements java.io.Serializable {

	// Fields

	private Integer staffId;
	private ShareDeptement shareDeptement;
	private String staffName;
	private Integer staffSex;
	private Integer staffAge;
	private String staffTel;
	private String staffAddress;
	private Date staffBirthday;
	private Integer staffLevel;
	private String staffPic;
	private String remark;
	private Integer status;
	private String adminName;

	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "staff_Id", unique = true, nullable = false)
	public Integer getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_Id")
	public ShareDeptement getShareDeptement() {
		return this.shareDeptement;
	}

	public void setShareDeptement(ShareDeptement shareDeptement) {
		this.shareDeptement = shareDeptement;
	}

	@Column(name = "staff_name", length = 50)
	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@Column(name = "staff_sex")
	public Integer getStaffSex() {
		return this.staffSex;
	}

	public void setStaffSex(Integer staffSex) {
		this.staffSex = staffSex;
	}

	@Column(name = "staff_age")
	public Integer getStaffAge() {
		return this.staffAge;
	}

	public void setStaffAge(Integer staffAge) {
		this.staffAge = staffAge;
	}

	@Column(name = "staff_tel", length = 50)
	public String getStaffTel() {
		return this.staffTel;
	}

	public void setStaffTel(String staffTel) {
		this.staffTel = staffTel;
	}

	@Column(name = "staff_address", length = 50)
	public String getStaffAddress() {
		return this.staffAddress;
	}

	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}

	@Column(name = "staff_birthday", length = 0)
	public Date getStaffBirthday() {
		return this.staffBirthday;
	}

	public void setStaffBirthday(Date staffBirthday) {
		this.staffBirthday = staffBirthday;
	}

	@Column(name = "staff_level")
	public Integer getStaffLevel() {
		return this.staffLevel;
	}

	public void setStaffLevel(Integer staffLevel) {
		this.staffLevel = staffLevel;
	}

	@Column(name = "staff_pic", length = 50)
	public String getStaffPic() {
		return this.staffPic;
	}

	public void setStaffPic(String staffPic) {
		this.staffPic = staffPic;
	}

	@Column(name = "remark", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "adminName",updatable=false)
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

}