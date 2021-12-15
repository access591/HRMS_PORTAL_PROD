package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name = "M_REGISTER")
public class Register implements Serializable {

	/**
	 * ACCESS SURENDRA
	 */
	private static final long serialVersionUID = -2786344630248161291L;
	@Id
	@Size(max = 15)
	@Column(name = "REG_CODE")
	private String regCode;
	@Size(max =100)
	@Column(name = "DESCRIPTION")
	private String descriptionReg ;
	
	@Size(max = 50)
    @Column(name = "INS_BY",updatable = false)
	private String insBy;
	
	@Column(name = "INS_DATE",updatable = false)
	private Date insDate =new Date();
	@Size(max = 50)
	@Column(name = "UPD_BY",insertable = false)
	private String updBy;
	
	@Column(name = "UPD_DATE",insertable = false)
	private Date  updDate = new Date();
	
	
	@Size(max = 1)
	@Column(name = "ACTIVE_YN")
	private String active;
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	
	public String getDescriptionReg() {
		return descriptionReg;
	}
	public void setDescriptionReg(String descriptionReg) {
		this.descriptionReg = descriptionReg.substring(0, 1).toUpperCase()+ descriptionReg.substring(1);
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
