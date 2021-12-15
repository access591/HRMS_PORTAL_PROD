package com.hrms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ARMS_LICENSE")
public class ArmsLicenses implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5657442637595445194L;

	@Id
	@Column(name = "ARMS_CODE")
	private String armsCode;

	@Size(max = 50)
	@Column(name = "NAME")
	private String name;

	@Size(max = 50)
	@Column(name = "FATHER_NAME")
	private String fatherName;

	@Size(max = 200)
	@Column(name = "ADDRESS_ARMS")
	private String addressArms;

//	@OneToOne
//	@JoinColumn(name = "STATE_CODE",updatable = false)
//	private State state;

	
	@Column(name = "CITY_CODE",updatable = false)
	private String city3;

	@Size(max = 30)
	@Column(name = "ARMS_AREA")
	private String armsArea;

	@Size(max = 30)
	@Column(name = "ARMS_LOCATION")
	private String armsLocation;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DOI")
	private Date doi;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DOV")
	private Date dov;
	
	@Size(max = 200)
	@Column(name = "TOA")
	private String toa;
	
	@Size(max = 100)
	@Column(name = "TOP")
	private String top;
	
	@Size(max = 100)
	@Column(name = "TYPE_OF_PERSON")
	private String typeOfPerson;
	
	

	@Column(name = "ARMS_NOL")
	private String armsNol;

	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	@Column(name = "UPD_BY", insertable = false)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false)
	private Date updDate = new Date();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMP_CODE")
	private Employee employee;

	@OneToMany(mappedBy = "armsLicenses",fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
	List<ArmsLicensesDetail> armsLicensesDetail;

	public ArmsLicenses() {
		super();
	
	}
	
	

	public String getTypeOfPerson() {
		return typeOfPerson;
	}



	public void setTypeOfPerson(String typeOfPerson) {
		this.typeOfPerson = typeOfPerson;
	}



	public String getArmsCode() {
		return armsCode;
	}

	public void setArmsCode(String armsCode) {
		this.armsCode = armsCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getAddressArms() {
		return addressArms;
	}

	public void setAddressArms(String addressArms) {
		this.addressArms = addressArms;
	}

	

	

//	public State getState() {
//		return state;
//	}
//
//	public void setState(State state) {
//		this.state = state;
//	}



	public String getArmsArea() {
		return armsArea;
	}

	public String getCity3() {
		return city3;
	}



	public void setCity3(String city3) {
		this.city3 = city3;
	}



	public void setArmsArea(String armsArea) {
		this.armsArea = armsArea;
	}

	public String getArmsLocation() {
		return armsLocation;
	}

	public void setArmsLocation(String armsLocation) {
		this.armsLocation = armsLocation;
	}

	
	public Date getDoi() {
		return doi;
	}



	public void setDoi(Date doi) {
		this.doi = doi;
	}



	public Date getDov() {
		return dov;
	}

	public void setDov(Date dov) {
		this.dov = dov;
	}

	public String getToa() {
		return toa;
	}

	public void setToa(String toa) {
		this.toa = toa;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getArmsNol() {
		return armsNol;
	}

	public void setArmsNol(String armsNol) {
		this.armsNol = armsNol;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<ArmsLicensesDetail> getArmsLicensesDetail() {
		return armsLicensesDetail;
	}

	public void setArmsLicensesDetail(List<ArmsLicensesDetail> armsLicensesDetail) {
		this.armsLicensesDetail = armsLicensesDetail;
	}
	
	

}
