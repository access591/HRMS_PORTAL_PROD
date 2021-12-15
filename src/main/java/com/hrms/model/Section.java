/**
 * 
 */
package com.hrms.model;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author Access
 *
 */
@Entity
@Table(name = "M_SECTION")

public class Section implements Serializable
{
	private static final long serialVersionUID = -4845127670430227293L;

	/**
	 * Mohit Access 
	 */
	

	@Id
	@Size(max =15)
	@Column(name = "SECT_CODE")
	private String sectionCode;
	@Size(max =50)
	@Column(name = "Sec_Desc")
	private String sectionDesc;
	@Size(max =12)
	@Column(name = "AMOUNT_LIMIT")
	private String amountLimit;
	@Size(max =1)
	@Column(name = "ACTIVE_YN")
	private String active;
	@Size(max =50)
    @Column(name = "INS_BY",updatable = false)
	private String insBy;
	
	@Column(name = "INS_DATE",updatable = false)
	private Date insDate =new Date();
	@Size(max =50)
	@Column(name = "UPD_BY",insertable = false)
	private String updBy;
	
	@Column(name = "UPD_DATE",insertable = false)
	private Date  updDate = new Date();
	
	
	public String getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	public String getAmountLimit() {
		return amountLimit;
	}
	public void setAmountLimit(String amountLimit) {
		this.amountLimit = amountLimit;
	}
	
	public String getSectionDesc() {
		return sectionDesc;
	}
	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc.substring(0, 1).toUpperCase()+ sectionDesc.substring(1);
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
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}

	
}	
	
	