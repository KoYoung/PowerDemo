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
 * SharePermitinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "share_permitinfo")
public class SharePermitinfo implements java.io.Serializable {

	// Fields

	private Integer perId;
	private String perName;
	private Set<ShareActioninfo> shareActioninfos = new HashSet<ShareActioninfo>(
			0);
	private Set<ShareRole> shareRoles = new HashSet<ShareRole>(0);

	// Constructors

	/** default constructor */
	public SharePermitinfo() {
	}

	/** full constructor */
	public SharePermitinfo(String perName,
			Set<ShareActioninfo> shareActioninfos, Set<ShareRole> shareRoles) {
		this.perName = perName;
		this.shareActioninfos = shareActioninfos;
		this.shareRoles = shareRoles;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "per_id", unique = true, nullable = false)
	public Integer getPerId() {
		return this.perId;
	}

	public void setPerId(Integer perId) {
		this.perId = perId;
	}

	@Column(name = "per_name", length = 50)
	public String getPerName() {
		return this.perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "share_per_action", joinColumns = { @JoinColumn(name = "per_id") }, inverseJoinColumns = { @JoinColumn(name = "action_id") })
	public Set<ShareActioninfo> getShareActioninfos() {
		return this.shareActioninfos;
	}

	public void setShareActioninfos(Set<ShareActioninfo> shareActioninfos) {
		this.shareActioninfos = shareActioninfos;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "sharePermitinfos")
	public Set<ShareRole> getShareRoles() {
		return this.shareRoles;
	}

	public void setShareRoles(Set<ShareRole> shareRoles) {
		this.shareRoles = shareRoles;
	}

}