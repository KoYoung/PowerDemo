package org.powerSystem.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * ShareAdmin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "share_admin")
public class ShareAdmin implements java.io.Serializable {

	// Fields

	private String adminName;
	private String adminPassword;
	private Integer staffId;
	private Timestamp adminDate;
	private String adminRemark;
	private Integer adminStatus;
	private Set<ShareRole> shareRoles = new HashSet<ShareRole>(0);

	// Constructors

	/** default constructor */
	public ShareAdmin() {
	}

	/** minimal constructor */
	public ShareAdmin(String adminName) {
		this.adminName = adminName;
	}

	/** full constructor */
	public ShareAdmin(String adminName, String adminPassword, Integer staffId,
			Timestamp adminDate, String adminRemark, Integer adminStatus,
			Set<ShareRole> shareRoles) {
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.staffId = staffId;
		this.adminDate = adminDate;
		this.adminRemark = adminRemark;
		this.adminStatus = adminStatus;
		this.shareRoles = shareRoles;
	}

	// Property accessors
	@Id
	@Column(name = "admin_name", unique = true, nullable = false, length = 30)
	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Column(name = "admin_password", length = 20)
	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Column(name = "staff_id")
	public Integer getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	@Column(name = "admin_date", length = 0)
	public Timestamp getAdminDate() {
		return this.adminDate;
	}

	public void setAdminDate(Timestamp adminDate) {
		this.adminDate = adminDate;
	}

	@Column(name = "admin_remark", length = 50)
	public String getAdminRemark() {
		return this.adminRemark;
	}

	public void setAdminRemark(String adminRemark) {
		this.adminRemark = adminRemark;
	}

	@Column(name = "admin_status")
	public Integer getAdminStatus() {
		return this.adminStatus;
	}

	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
	}

//	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "shareAdmins")
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "share_admin_role", joinColumns = { @JoinColumn(name = "admin_name", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", updatable = false) })
	public Set<ShareRole> getShareRoles() {
		return this.shareRoles;
	}

	public void setShareRoles(Set<ShareRole> shareRoles) {
		this.shareRoles = shareRoles;
	}

}