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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ShareDeptement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "share_deptement")
public class ShareDeptement implements java.io.Serializable {

	private Integer deptId;
	private String deptName;
	private Integer deptParent;
	private Integer deptSort;


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "dept_id", unique = true, nullable = false)
	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Column(name = "dept_name", length = 20)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "dept_parent")
	public Integer getDeptParent() {
		return this.deptParent;
	}

	public void setDeptParent(Integer deptParent) {
		this.deptParent = deptParent;
	}

	@Column(name = "dept_sort")
	public Integer getDeptSort() {
		return this.deptSort;
	}

	public void setDeptSort(Integer deptSort) {
		this.deptSort = deptSort;
	}
}