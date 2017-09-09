package org.powerSystem.entity.mem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MemRank entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mem_rank")
public class MemRank implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rankId;
	private String rankName;
	private Float discount;
	private Integer scoreLimit;

	// Constructors

	/** default constructor */
	public MemRank() {
	}

	/** full constructor */
	public MemRank(String rankName, Float discount, Integer scoreLimit) {
		this.rankName = rankName;
		this.discount = discount;
		this.scoreLimit = scoreLimit;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "rank_Id", unique = true, nullable = false)
	public Integer getRankId() {
		return this.rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}

	@Column(name = "rank_Name", length = 20)
	public String getRankName() {
		return this.rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	@Column(name = "discount", precision = 12, scale = 0)
	public Float getDiscount() {
		return this.discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	@Column(name = "scoreLimit")
	public Integer getScoreLimit() {
		return this.scoreLimit;
	}

	public void setScoreLimit(Integer scoreLimit) {
		this.scoreLimit = scoreLimit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MemRank(Integer rankId, String rankName, Float discount,
			Integer scoreLimit) {
		super();
		this.rankId = rankId;
		this.rankName = rankName;
		this.discount = discount;
		this.scoreLimit = scoreLimit;
	}

}