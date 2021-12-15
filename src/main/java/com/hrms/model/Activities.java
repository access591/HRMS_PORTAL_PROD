package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name = "M_ACTIV")
public class Activities implements Serializable{
	/**
	 * Access Surendra Kumar Meena
	 */
	private static final long serialVersionUID = -7265729884533931859L;
	@Id
	@Size(max = 15)
	@Column(name = "ACT_CODE")
	private String actCode;

	@Size(max = 40)
	@Column(name = "ACT_NAME")
	private String actName;

	@Size(max = 1)
	@Column(name = "ACTIVE_YN")
	private String active;
	
	@Size(max = 50)
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();
	
	@Size(max = 50)
	@Column(name = "UPD_BY", insertable = false)
	private String updBy;
	
	@Column(name = "UPD_DATE", insertable = false)
	private Date  updDate = new Date();
	
	
	public String getActCode() {
		return actCode;
	}
	public void setActCode(String actCode) {
		this.actCode = actCode;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName.substring(0, 1).toUpperCase()+ actName.substring(1);
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
