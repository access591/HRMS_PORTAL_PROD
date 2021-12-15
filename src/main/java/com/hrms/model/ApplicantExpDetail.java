package com.hrms.model;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="APPLICANT_EXP_DTL")
public class ApplicantExpDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "Appli_Exp_Detailid")
	private Long appliExpDetailId;
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	@Column(name="LAST_SALARY")
	private Long lastSalary;
	
	@Column(name="EXP_IN_MONTH")
	private int expInMonth;
	
	@Column(name="WORK_PROFILE")
	private String workProfile;
	
	@Temporal(TemporalType.DATE)
	@Column(name="APPLICANT_DATE")
	private Date applicantDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLI_CODE",updatable = false)
	private ApplicantInfo applicantInfo;

	public ApplicantExpDetail() {
		super();
		
	}

	public Long getAppliExpDetailId() {
		return appliExpDetailId;
	}

	public void setAppliExpDetailId(Long appliExpDetailId) {
		this.appliExpDetailId = appliExpDetailId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getLastSalary() {
		return lastSalary;
	}

	public void setLastSalary(Long lastSalary) {
		this.lastSalary = lastSalary;
	}

	public int getExpInMonth() {
		return expInMonth;
	}

	public void setExpInMonth(int expInMonth) {
		this.expInMonth = expInMonth;
	}

	public String getWorkProfile() {
		return workProfile;
	}

	public void setWorkProfile(String workProfile) {
		this.workProfile = workProfile;
	}

	public Date getApplicantDate() {
		return applicantDate;
	}

	public void setApplicantDate(Date applicantDate) {
		this.applicantDate = applicantDate;
	}

	public ApplicantInfo getApplicantInfo() {
		return applicantInfo;
	}

	public void setApplicantInfo(ApplicantInfo applicantInfo) {
		this.applicantInfo = applicantInfo;
	}
	
	
	
	
	

}
