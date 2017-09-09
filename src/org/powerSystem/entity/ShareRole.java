package org.powerSystem.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * ShareRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "share_role")
public class ShareRole implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private Set<ShareAdmin> shareAdmins = new HashSet<ShareAdmin>(0);
	private Set<SharePermitinfo> sharePermitinfos = new HashSet<SharePermitinfo>(
			0);

	// Constructors

	/** default constructor */
	public ShareRole() {
	}

	/** full constructor */
	public ShareRole(String roleName, Set<ShareAdmin> shareAdmins,
			Set<SharePermitinfo> sharePermitinfos) {
		this.roleName = roleName;
		this.shareAdmins = shareAdmins;
		this.sharePermitinfos = sharePermitinfos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_name", length = 20)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,mappedBy="shareRoles")
	//@JoinTable(name = "share_admin_role", joinColumns = { @JoinColumn(name = "role_id", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "admin_name", updatable = false) })
	public Set<ShareAdmin> getShareAdmins() {
		return this.shareAdmins;
	}

	public void setShareAdmins(Set<ShareAdmin> shareAdmins) {
		this.shareAdmins = shareAdmins;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "share_role_per", joinColumns = { @JoinColumn(name = "role_id", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "per_id", updatable = false) })
	public Set<SharePermitinfo> getSharePermitinfos() {
		return this.sharePermitinfos;
	}

	public void setSharePermitinfos(Set<SharePermitinfo> sharePermitinfos) {
		this.sharePermitinfos = sharePermitinfos;
	}

}