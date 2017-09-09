package org.powerSystem.entity.mem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MemScoreRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mem_score_rule")
public class MemScoreRule implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ruleId;
	private Float money;
	private Integer score;
	private String typeId;

	// Constructors

	/** default constructor */
	public MemScoreRule() {
	}

	/** full constructor */
	public MemScoreRule(Float money, Integer score, String typeId) {
		this.money = money;
		this.score = score;
		this.typeId = typeId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "rule_Id", unique = true, nullable = false)
	public Integer getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	@Column(name = "money", precision = 12, scale = 0)
	public Float getMoney() {
		return this.money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	@Column(name = "score")
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "type_Id")
	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

}