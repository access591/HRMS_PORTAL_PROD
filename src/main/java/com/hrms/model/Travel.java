package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name = "M_TRAVEL")
public class Travel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2540893849572427408L;
   
	@Id
	@Size(max = 15)
	@Column(name = "TRAVEL_CODE")
	private String travelCode;
	@Size(max = 15)
	@Column(name ="STRT_CITY_CODE")
	private String strtCityCode;
	@Size(max = 50)
	@Column(name ="STRT_CITY_NAME")
	private String strtCityName;
	@Size(max = 15)
	@Column(name ="VISIT_CITY_CODE")
	private String visitCityCode;
	@Size(max = 50)
	@Column(name ="VISIT_CITY_NAME")
	private String visitCityName;
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

	public String getTravelCode() {
		return travelCode;
	}

	public void setTravelCode(String travelCode) {
		this.travelCode = travelCode;
	}

	public String getStrtCityCode() {
		return strtCityCode;
	}

	public void setStrtCityCode(String strtCityCode) {
		this.strtCityCode = strtCityCode;
	}

	public String getStrtCityName() {
		return strtCityName;
	}

	public void setStrtCityName(String strtCityName) {
		this.strtCityName = strtCityName;
	}

	public String getVisitCityCode() {
		return visitCityCode;
	}

	public void setVisitCityCode(String visitCityCode) {
		this.visitCityCode = visitCityCode;
	}

	public String getVisitCityName() {
		return visitCityName;
	}

	public void setVisitCityName(String visitCityName) {
		this.visitCityName = visitCityName;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	
	
	
}
