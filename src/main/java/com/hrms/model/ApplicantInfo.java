package com.hrms.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="APPLICANT_INFO")
public class ApplicantInfo {
	
	@Id
	@Column(name="APPLI_CODE")
	@Size(max=50)
	private String applicantCode;
	
//	@Size(max=50)
//	@Column(name="ADVT_CODE")
//	private String advtCode;
	
	@ManyToOne
	@JoinColumn(name="ADVT_CODE")
	private ReqAdvertisement reqAdvertisement;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="ADVT_DATE")
	private String advtDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="APPLI_DATE")
	private Date applicantDate;
	
	@Size(max=20)
	@Column(name="SEX")
	private String sex;
	
	@Size(max=50)
	@Column(name="FATHER_NAME")
	private String fatherName;
	
	@Size(max=50)
	@Column(name="MOTHER_NAME")
	private String motherName;
	
	@Size(max=100)
	@Column(name="EMAIL_ADD")
	private String emailAdd;
	
	@Size(max=20)
	@Column(name="MOBILE_NO")
	private String mobileNo;
	
	@Size(max=250)
	@Column(name="CORR_ADD")
	private String corrAdd;
	
	@Size(max=50)
	@Column(name="CORR_CITY")
	private String corrCity;
	
	@Size(max=50)
	@Column(name="CORR_STATE")
	private String corrState;
	
	@Size(max=20)
	@Column(name="CORR_STD_PHONE")
	private String corrStdPhone;
	
	@Size(max=250)
	@Column(name="PERM_ADD")
	private String permAdd;
	
	@Size(max=50)
	@Column(name="PERM_CITY")
	private String permCity;
	
	@Size(max=50)
	@Column(name="PERM_STATE")
	private String permState;
	
	@Size(max=20)
	@Column(name="PERM_STD_PHONE")
	private String permStdPhone;
	
	@Size(max=100)
	@Column(name="ACAD_QUALI")
	private String acadQuali;
	
	@Size(max=100)
	@Column(name="TECH_QUALI")
	private String techQuali;
	
	@Size(max=50)
	@Column(name="LANG_HINDI")
	private String langHindi;
	
	@Size(max=50)
	@Column(name="LANG_ENGLISH")
	private String langEnglish;
	
	@Size(max=50)
	@Column(name="LANG_PUNJABI")
	private String landPunjabi;
	
	@Size(max=50)
	@Column(name="LANG_OTHER_NAME")
	private String langOtherName;
	
	@Size(max=50)
	@Column(name="LANG_OTHER_FLUENCY")
	private String langOthersRw;
	
	@Size(max=150)
	@Column(name="CURRENT_CTC")
	private String currentCtc;
	
	@Size(max=150)
	@Column(name="EXPECTED_CTC")
	private String expectedCtc;
	
	@Size(max=250)
	@Column(name="REASON_LEAVE")
	private String reasonToLeave;
	
	@Size(max=50)
	@Column(name="MARITAL_STATUS")
	private String maritalStatus;
	
	@Size(max=50)
	@Column(name="MODE_OF_REF")
	private String modeOfRef;
	
	@Size(max=100)
	@Column(name="EMP_CODE")
	private String empCode;
	
	@Size(max=50)
	@Column(name="APPLIED_BEFORE")
	private String appliedBefore;
	
	@Size(max=100)
	@Column(name="APPLIED_CODE_BEFORE")
	private String applicantCodeBefore;
	
	@Column(name="APPLIED_DATE_BEFORE")
	private Date appliDateBefore;
	
	@Size(max=150)
	@Column(name="HOBBIES")
	private String hobbies;
	
	@Size(max=50)
	@Column(name="INS_BY")
	private String insBy;
	
	@Column(name="INS_DATE")
	private Date insDate;
	
	@Column(name="APPLICANT_RESUME")
	private String applicantResume;
	
	@Size(max=50)
	@Column(name="APPLICANT_NAME")
	private String applicantName;
	
	@Column(name="DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	@Size(max=20)
	@Column(name="INTER_STATUS")
	private String interStatus = "N";
	
	@ManyToOne
	@JoinColumn(name="DESIG_CODE")
	private Designation desigCode;
	
	@Size(max=250)
	@Column(name="CORR_ADD2")
	private String corrAdd2;
	
	@Size(max=250)
	@Column(name="PERM_ADD2")
	private String permAdd2;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="applicantInfo",cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private List<ApplicantExpDetail> applicantExpDetail;

	
	public ApplicantInfo() {
		super();
		
	}

	
	
	public List<ApplicantExpDetail> getApplicantExpDetail() {
		return applicantExpDetail;
	}



	public void setApplicantExpDetail(List<ApplicantExpDetail> applicantExpDetail) {
		this.applicantExpDetail = applicantExpDetail;
	}



	public String getApplicantCode() {
		return applicantCode;
	}

	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}

	public String getAdvtDate() {
		return advtDate;
	}

	public void setAdvtDate(String advtDate) {
		this.advtDate = advtDate;
	}

	public Date getApplicantDate() {
		return applicantDate;
	}

	public void setApplicantDate(Date applicantDate) {
		this.applicantDate = applicantDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getEmailAdd() {
		return emailAdd;
	}

	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCorrAdd() {
		return corrAdd;
	}

	public void setCorrAdd(String corrAdd) {
		this.corrAdd = corrAdd;
	}

	public String getCorrCity() {
		return corrCity;
	}

	public void setCorrCity(String corrCity) {
		this.corrCity = corrCity;
	}

	public String getCorrState() {
		return corrState;
	}

	public void setCorrState(String corrState) {
		this.corrState = corrState;
	}

	public String getCorrStdPhone() {
		return corrStdPhone;
	}

	public void setCorrStdPhone(String corrStdPhone) {
		this.corrStdPhone = corrStdPhone;
	}

	public String getPermAdd() {
		return permAdd;
	}

	public void setPermAdd(String permAdd) {
		this.permAdd = permAdd;
	}

	public String getPermCity() {
		return permCity;
	}

	public void setPermCity(String permCity) {
		this.permCity = permCity;
	}

	public String getPermState() {
		return permState;
	}

	public void setPermState(String permState) {
		this.permState = permState;
	}

	public String getPermStdPhone() {
		return permStdPhone;
	}

	public void setPermStdPhone(String permStdPhone) {
		this.permStdPhone = permStdPhone;
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

	public String getLangHindi() {
		return langHindi;
	}

	public void setLangHindi(String langHindi) {
		this.langHindi = langHindi;
	}

	public String getLangEnglish() {
		return langEnglish;
	}

	public void setLangEnglish(String langEnglish) {
		this.langEnglish = langEnglish;
	}

	public String getLandPunjabi() {
		return landPunjabi;
	}

	public void setLandPunjabi(String landPunjabi) {
		this.landPunjabi = landPunjabi;
	}

	public String getLangOtherName() {
		return langOtherName;
	}

	public void setLangOtherName(String langOtherName) {
		this.langOtherName = langOtherName;
	}

	public String getLangOthersRw() {
		return langOthersRw;
	}

	public void setLangOthersRw(String langOthersRw) {
		this.langOthersRw = langOthersRw;
	}

	public String getCurrentCtc() {
		return currentCtc;
	}

	public void setCurrentCtc(String currentCtc) {
		this.currentCtc = currentCtc;
	}

	public String getExpectedCtc() {
		return expectedCtc;
	}

	public void setExpectedCtc(String expectedCtc) {
		this.expectedCtc = expectedCtc;
	}

	public String getReasonToLeave() {
		return reasonToLeave;
	}

	public void setReasonToLeave(String reasonToLeave) {
		this.reasonToLeave = reasonToLeave;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getModeOfRef() {
		return modeOfRef;
	}

	public void setModeOfRef(String modeOfRef) {
		this.modeOfRef = modeOfRef;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getAppliedBefore() {
		return appliedBefore;
	}

	public void setAppliedBefore(String appliedBefore) {
		this.appliedBefore = appliedBefore;
	}

	public String getApplicantCodeBefore() {
		return applicantCodeBefore;
	}

	public void setApplicantCodeBefore(String applicantCodeBefore) {
		this.applicantCodeBefore = applicantCodeBefore;
	}

	public Date getAppliDateBefore() {
		return appliDateBefore;
	}

	public void setAppliDateBefore(Date appliDateBefore) {
		this.appliDateBefore = appliDateBefore;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
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

	public String getApplicantResume() {
		return applicantResume;
	}

	public void setApplicantResume(String applicantResume) {
		this.applicantResume = applicantResume;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getInterStatus() {
		return interStatus;
	}

	public void setInterStatus(String interStatus) {
		this.interStatus = interStatus;
	}

	

	public Designation getDesigCode() {
		return desigCode;
	}



	public void setDesigCode(Designation desigCode) {
		this.desigCode = desigCode;
	}



	public String getCorrAdd2() {
		return corrAdd2;
	}

	public void setCorrAdd2(String corrAdd2) {
		this.corrAdd2 = corrAdd2;
	}

	public String getPermAdd2() {
		return permAdd2;
	}

	public void setPermAdd2(String permAdd2) {
		this.permAdd2 = permAdd2;
	}



	public ReqAdvertisement getReqAdvertisement() {
		return reqAdvertisement;
	}



	public void setReqAdvertisement(ReqAdvertisement reqAdvertisement) {
		this.reqAdvertisement = reqAdvertisement;
	}
	
	
	
	
	
	
	
	

}
