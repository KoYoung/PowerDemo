package org.powerSystem.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name = "share_actioninfo")
public class ShareActioninfo implements java.io.Serializable {


	private Integer actionId;
	private String actionname;
	private Integer actionParent;//null 最顶的
	private String actionPic;
	private String actionUrl;
	private Integer actionSort;
	private Set<SharePermitinfo> sharePermitinfos = new HashSet<SharePermitinfo>(
			0);

	// Constructors

	/** default constructor */
	public ShareActioninfo() {
	}

	/** full constructor */
	public ShareActioninfo(String actionname, Integer actionParent,
			String actionPic, Set<SharePermitinfo> sharePermitinfos) {
		this.actionname = actionname;
		this.actionParent = actionParent;
		this.actionPic = actionPic;
		this.sharePermitinfos = sharePermitinfos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "action_id", unique = true, nullable = false)
	public Integer getActionId() {
		return this.actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	@Column(name = "action_name", length = 20)
	public String getActionname() {
		return this.actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	@Column(name = "action_parent")
	public Integer getActionParent() {
		return this.actionParent;
	}

	public void setActionParent(Integer actionParent) {
		this.actionParent = actionParent;
	}

	@Column(name = "action_pic", length = 50)
	public String getActionPic() {
		return this.actionPic;
	}

	public void setActionPic(String actionPic) {
		this.actionPic = actionPic;
	}

	@ManyToMany(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY, mappedBy = "shareActioninfos")
	public Set<SharePermitinfo> getSharePermitinfos() {
		return this.sharePermitinfos;
	}

	public void setSharePermitinfos(Set<SharePermitinfo> sharePermitinfos) {
		this.sharePermitinfos = sharePermitinfos;
	}
	@Column(name="action_url", length=50)
	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	@Column(name = "action_sort", length=50)
	public Integer getActionSort() {
		return actionSort;
	}

	public void setActionSort(Integer actionSort) {
		this.actionSort = actionSort;
	}

}