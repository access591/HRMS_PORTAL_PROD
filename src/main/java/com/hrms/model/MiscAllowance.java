package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name ="M_ALLOWANCE_DEDUCTION")
public class MiscAllowance implements Serializable {

	/**
	 * 
	 *  @author Access Surendra
	 */
private static final long serialVersionUID = -1201231658058617763L;
	
	@Id
	@Size(max =15)
	@Column(name ="ALLOWANCE_CODE")
	private String allowanceCode;
	@Size(max =20)
	@Column(name ="HEAD")
	private String head;
	@Size(max =15)
	@Column(name ="ACT_CODE")
	private String actCode;
	@Size(max =25)
	@Column(name ="SUB_GROUP_CODE")
	private String subGroupCode;
	@Size(max =1)
	@Column(name ="ACTIVE")
	private String active;
	@Size(max =100)
	@Column(name ="DESCRIPTION")
	private String description;
	@Size(max =25)
	@Column(name ="TYPE")
	private String type;
	@Size(max =100)
	@Column(name ="ACCOUNT_NAME")
	private String accountName;
	@Size(max =100)
	@Column(name ="SUB_GROUP_NAME")
	private String subGroupName;
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

	public String getAllowanceCode() {
		return allowanceCode;
	}

	public void setAllowanceCode(String allowanceCode) {
		this.allowanceCode = allowanceCode;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getActCode() {
		return actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}

	public String getSubGroupCode() {
		return subGroupCode;
	}

	public void setSubGroupCode(String subGroupCode) {
		this.subGroupCode = subGroupCode;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description.substring(0, 1).toUpperCase()+ description.substring(1);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getSubGroupName() {
		return subGroupName;
	}

	public void setSubGroupName(String subGroupName) {
		this.subGroupName = subGroupName;
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
