package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "M_CITY")
public class City implements Serializable {

	/**
	 *  @author Access Surendra
	 */
	private static final long serialVersionUID = -4022504081712702959L;
	@Id
	@Size(max =15)
	@Column(name = "CITY_CODE")
	private String cityCode;
	@Size(max =50)
	@Column(name = "CITY_NAME")
	private String cityName;
	@Size(max =1)
	@Column(name = "ACTIVE_YN")
	private String active;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATE_CODE",updatable = false)
	private State stateCode;
	
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_CODE",updatable = false)
	private Country countryCode;
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
	

	
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName.toUpperCase();
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
	
	public State getStateCode() {
		return stateCode;
	}
	public void setStateCode(State stateCode) {
		this.stateCode = stateCode;
	}
	public Country getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(Country countryCode) {
		this.countryCode = countryCode;
	}

	
	
	
}
