package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name = "M_SKILLCATEGORY")
public class SkillCategory implements Serializable {

	/**
	 * 
	 * ACCESS  SURENDRA
	 *  
	 * 
	 */
private static final long serialVersionUID = -5018920894314776560L;
	@Id
	@Size(max = 15)
	@Column(name = "SKILL_CODE")
	private String skillLevelCode;
	@Size(max = 10)
	@Column(name = "SKILL_LEVEL")
	private String skillLevel;
	@Size(max = 50)
	@Column(name = "SKILL_PARA_DESC")
	private String skillParaDes;
	@Size(max = 50)
	@Column(name = "SKILL_CAT_DESC")
	private String skillCatDesc;
	@Size(max = 10)
	@Column(name = "SKILL_PARA_CODE")
	private String skillParaCode;
	@Size(max = 12)
	@Column(name = "SKILL_PARA_MAX_MARK")
	private String skillParaMaxMark;
	@Size(max = 50)
	@Column(name = "INS_BY", updatable = false)
	private String insBy;
	
	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();
	@Size(max = 50)
	@Column(name = "UPD_BY", insertable = false)
	private String updBy;
	
	@Column(name = "UPD_DATE", insertable = false)
	private Date updDate = new Date();
	
	@Size(max =1)
	@Column(name = "ACTIVE_YN")
	private String active;
	
	public String getSkillLevelCode() {
		return skillLevelCode;
	}
	public void setSkillLevelCode(String skillLevelCode) {
		this.skillLevelCode = skillLevelCode;
	}
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel.substring(0, 1).toUpperCase()+ skillLevel.substring(1);;
	}
	public String getSkillParaDes() {
		return skillParaDes;
	}
	public void setSkillParaDes(String skillParaDes) {
		this.skillParaDes = skillParaDes.substring(0, 1).toUpperCase()+ skillParaDes.substring(1);
	}
	public String getSkillCatDesc() {
		return skillCatDesc;
	}
	public void setSkillCatDesc(String skillCatDesc) {
		this.skillCatDesc = skillCatDesc.substring(0, 1).toUpperCase()+ skillCatDesc.substring(1);
	}
	public String getSkillParaCode() {
		return skillParaCode;
	}
	public void setSkillParaCode(String skillParaCode) {
		this.skillParaCode = skillParaCode;
	}
	public String getSkillParaMaxMark() {
		return skillParaMaxMark;
	}
	public void setSkillParaMaxMark(String skillParaMaxMark) {
		this.skillParaMaxMark = skillParaMaxMark;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getInsBy() {
		return insBy;
	}
	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}
	public Date getInsDate() {
		return insDate;
	}
	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	
	
	
	
	
	}
