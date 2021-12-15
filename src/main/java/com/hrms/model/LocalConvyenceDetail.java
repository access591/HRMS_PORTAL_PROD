package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "EMP_LOCAL_RMBSMT_DTL")
public class LocalConvyenceDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4842749599564330971L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "LOCAL_CONV_ID",updatable = false)
	private LocalConvyence localConvId;
	
	@ManyToOne
	@JoinColumn(name ="LOCAL_CONV_DT",updatable = false)
	private LocalConvyence localConvDate;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CON_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  locDate;
	
	@Column(name = "START_PLACE")
	private String  startPlace;
	
	@Column(name = "VISIT_PLACE")
	private String  vistPlace;
	
	@Column(name = "VISIT_PURPOSE")
	private String  purposeOfVist;
	
	@Column(name = "MODE_OF_TRAVEL")
	private String  modeOfTravel; 
	
	
	@Column(name = "DISTANCE")
	private String  distanceKm;
	
	@Column(name = "LTA_RATE")
	private String  ltaRate;
	
	@Column(name = "ACTUAL_AMOUNT")
	private String  actualAmount;
	
	@Column(name = "CLAIMED_AMOUNT")
	private String  claimedAmount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalConvyence getLocalConvId() {
		return localConvId;
	}

	public void setLocalConvId(LocalConvyence localConvId) {
		this.localConvId = localConvId;
	}

	public LocalConvyence getLocalConvDate() {
		return localConvDate;
	}

	public void setLocalConvDate(LocalConvyence localConvDate) {
		this.localConvDate = localConvDate;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public Date getLocDate() {
		return locDate;
	}

	public void setLocDate(Date locDate) {
		this.locDate = locDate;
	}

	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getVistPlace() {
		return vistPlace;
	}

	public void setVistPlace(String vistPlace) {
		this.vistPlace = vistPlace;
	}

	public String getPurposeOfVist() {
		return purposeOfVist;
	}

	public void setPurposeOfVist(String purposeOfVist) {
		this.purposeOfVist = purposeOfVist;
	}

	public String getModeOfTravel() {
		return modeOfTravel;
	}

	public void setModeOfTravel(String modeOfTravel) {
		this.modeOfTravel = modeOfTravel;
	}

	
	public String getDistanceKm() {
		return distanceKm;
	}

	public void setDistanceKm(String distanceKm) {
		this.distanceKm = distanceKm;
	}

	public String getLtaRate() {
		return ltaRate;
	}

	public void setLtaRate(String ltaRate) {
		this.ltaRate = ltaRate;
	}

	public String getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getClaimedAmount() {
		return claimedAmount;
	}

	public void setClaimedAmount(String claimedAmount) {
		this.claimedAmount = claimedAmount;
	}
	
	
	

	 
}
