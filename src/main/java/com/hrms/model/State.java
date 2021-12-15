package com.hrms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name = "M_STATE")
public class State implements Serializable{

	/**
	 *  @author Access Surendra
	 */
	private static final long serialVersionUID = -6376128149451393606L;
	@Id
	@Size(max = 15)
	@Column(name = "STATE_CODE")
	private String stateCode;
	@Size(max = 50)
	@Column(name = "STATE_NAME")
	private String stateName;
	@Size(max = 1)
	@Column(name = "ACTIVE_YN")
	private String active;
	
	@OneToMany(mappedBy = "stateCode")
	private List<City> citys;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_CODE", updatable = false)
	private Country countryCode;
	
	@Size(max =50)
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();
	@Size(max =50)
	@Column(name = "UPD_BY", insertable = false)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false)
	private Date updDate = new Date();

	
	public Country getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(Country countryCode) {
		this.countryCode = countryCode;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName.toUpperCase();
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public List<City> getCitys() {
		return citys;
	}
	public void setCitys(List<City> citys) {
		this.citys = citys;
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
