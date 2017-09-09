package org.powerSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShareDataBase entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "share_data_base")
public class ShareDataBase implements java.io.Serializable {

	// Fields

	private Integer baseId;
	private Integer baseNo;
	private String baseName;
	private Integer baseParent;

	// Constructors

	/** default constructor */
	public ShareDataBase() {
	}

	/** minimal constructor */
	public ShareDataBase(Integer baseNo) {
		this.baseNo = baseNo;
	}

	/** full constructor */
	public ShareDataBase(Integer baseNo, String baseName, Integer baseParent) {
		this.baseNo = baseNo;
		this.baseName = baseName;
		this.baseParent = baseParent;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "base_id", unique = true, nullable = false)
	public Integer getBaseId() {
		return this.baseId;
	}

	public void setBaseId(Integer baseId) {
		this.baseId = baseId;
	}

	@Column(name = "base_no", nullable = false)
	public Integer getBaseNo() {
		return this.baseNo;
	}

	public void setBaseNo(Integer baseNo) {
		this.baseNo = baseNo;
	}

	@Column(name = "base_name", length = 20)
	public String getBaseName() {
		return this.baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	@Column(name = "base_parent")
	public Integer getBaseParent() {
		return this.baseParent;
	}

	public void setBaseParent(Integer baseParent) {
		this.baseParent = baseParent;
	}

}