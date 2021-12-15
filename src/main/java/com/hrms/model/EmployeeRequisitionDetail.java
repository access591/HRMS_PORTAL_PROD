package com.hrms.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name="EPLOYEE_RQUISITION_DETAIL")
public class EmployeeRequisitionDetail implements Serializable{

	
	private static final long serialVersionUID = -4728476448805913732L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REQDETAILCODE")
	private Long reqDetailCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQ_CODE",nullable=false)
	private EmployeeRequisition employeeRequisition;
	
	@Column(name="REQ_DATE")
	private Date reqDate;
	
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DESIG_CODE",updatable = false)
	private Designation designation;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="SEX")
	private String sex;
	
	@Column(name="AGE_FROM")
	private int ageFrom;
	
	@Column(name="AGE_TO")
	private int ageTo;
	
	@Column(name="ACCADEMIC_QUALIFICATION")
	private String acadQuali;
	
	@Column(name="TECHNICAL_QUALIFICATION")
	private String techQuali;
	
	@Column(name="EXPERIENCE")
	private int experience;
	
	@Column(name="VACANCY_TYPE")
	private String vacanncyType;
	
	@Column(name="TOTAL_VACANCY")
	private int totalVacancy;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="MIN_EXPERIENCE")
	private int minExperience;
	
	@Column(name="MAX_EXPERIENCE")
	private int maxExperience;
	
	@Column(name="SALARY_FROM")   
	private String salaryFrom;
	
	@Column(name="SALARY_TO")
	private String salaryTo;
	
	

	public EmployeeRequisitionDetail() {
		super();
		
	}

	public EmployeeRequisitionDetail(String location, String sex,
			int ageFrom, int ageTo, String acadQuali, String techQuali, int experience, String vacanncyType,
			int totalVacancy, String remarks, int minExperience, int maxExperience, String salaryFrom, String salaryTo) {
		super();
		
		
		
		this.location = location;
		this.sex = sex;
		this.ageFrom = ageFrom;
		this.ageTo = ageTo;
		this.acadQuali = acadQuali;
		this.techQuali = techQuali;
		this.experience = experience;
		this.vacanncyType = vacanncyType;
		this.totalVacancy = totalVacancy;
		this.remarks = remarks;
		this.minExperience = minExperience;
		this.maxExperience = maxExperience;
		this.salaryFrom = salaryFrom;
		this.salaryTo = salaryTo;
	}
	
	
	

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Long getReqDetailCode() {
		return reqDetailCode;
	}

	public void setReqDetailCode(Long reqDetailCode) {
		this.reqDetailCode = reqDetailCode;
	}

	
	
	@JsonBackReference
	public EmployeeRequisition getEmployeeRequisition() {
		return employeeRequisition;
	}

	public void setEmployeeRequisition(EmployeeRequisition employeeRequisition) {
		this.employeeRequisition = employeeRequisition;
	}

	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAgeFrom() {
		return ageFrom;
	}

	public void setAgeFrom(int ageFrom) {
		this.ageFrom = ageFrom;
	}

	public int getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(int ageTo) {
		this.ageTo = ageTo;
	}

	public String getAcadQuali() {
		return acadQuali;
	}

	public void setAcadQuali(String acadQuali) {
		this.acadQuali = acadQuali;
	}

	public String getTechQuali() {
		return techQuali;
	}

	public void setTechQuali(String techQuali) {
		this.techQuali = techQuali;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getVacanncyType() {
		return vacanncyType;
	}

	public void setVacanncyType(String vacanncyType) {
		this.vacanncyType = vacanncyType;
	}

	public int getTotalVacancy() {
		return totalVacancy;
	}

	public void setTotalVacancy(int totalVacancy) {
		this.totalVacancy = totalVacancy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getMinExperience() {
		return minExperience;
	}

	public void setMinExperience(int minExperience) {
		this.minExperience = minExperience;
	}

	public int getMaxExperience() {
		return maxExperience;
	}

	public void setMaxExperience(int maxExperience) {
		this.maxExperience = maxExperience;
	}

	public String getSalaryFrom() {
		return salaryFrom;
	}

	public void setSalaryFrom(String salaryFrom) {
		this.salaryFrom = salaryFrom;
	}

	public String getSalaryTo() {
		return salaryTo;
	}

	public void setSalaryTo(String salaryTo) {
		this.salaryTo = salaryTo;
	}

	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}
	
	
	
	
}
