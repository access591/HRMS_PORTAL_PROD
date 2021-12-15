package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "M_EMPLOYEE")
public class Employee implements Serializable {


	private static final long serialVersionUID = -615585907130702990L;


	@Id
	@Size(max = 25)
	@Column(name ="EMPLOYEE_CODE")
	private String empCode;
	
	@Size(max = 200)
	@Column(name = "EMPLOYEE_NAME")
	private String empName;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CATEGORY_CODE",updatable = false)
	private Category category;
	
	@Column(name = "PAYEE_CODE")
	private String payeeCode;
	
	@Column(name = "HOME_DISTRICT")
	private String homeDistrict;
	
	@Column(name = "DEPARTMENT_COURT")
	private String departmentCourt;
	
	@Column(name = "ORDER_NUMBER")
	private String orederNumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	
	@Column(name = "TYPE")
	private String type;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "EMP_DOB")
	private Date dob;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DATE_OF_JOINING")
	private Date doj;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DATE_OF_POSTING")
	private Date dop;
	
	@Column(name = "PLACE_PRESENTING_POSTING")
	private String placePresentPosting;
	
	@Size(max = 20)
	@Column(name ="BATCH_YEAR")
	private String batchYear;
	
	
	@Column(name = "PAN_NO")
	private String panNo;
	
	@Size(max =16)
	@Column(name = "AADHAR_NO")
	private String aadharNo;
	
	@Size(max =1)
	@Column(name = "GENDER")
	private String gender;
	
	@Size(max =255)
	@Column(name = "MARTIAL_STATUS")
	private String martialStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name ="DATE_OF_RETIREMENT")
	private Date dor;
	
	@Column(name = "ACTIVE_STATUS")
	private String activeStatus;
	
	@Size(max =200)
	@Column(name = "QUALIFICATION")
	private String qualification;
	
	@Size(max =100)
	@Column(name = "EMAIL")
	private String email;
	
	@Size(max =12)
	@Column(name = "TELEPHONE")
	private String telephone;
	@Size(max =10)
	@Column(name = "MOBILE_NUMBER1")
	private String mobileNumber1;
	@Size(max =10)
	@Column(name = "MOBILE_NUMBER2")
	private String mobileNumber2;
	
	@Size(max =200)
	@Column(name = "CORR_ADD1")
	private String corrAdd1;
	
	@Size(max =200)
	@Column(name = "CORR_ADD2")
	private String corrAdd2;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COR_CITY_CODE",updatable = false)
	private City city1;
	
//	@OneToOne
//	@JoinColumn(name="COR_COUNTRY_CODE",updatable = false)
//	private Country country1;
	
//	@OneToOne
//	@JoinColumn(name="COR_STATE_CODE",updatable = false)
//	private State state1;
	
	@Size(max =200)
	@Column(name = "CORR_CONTACT_NUMBER")
	private String corrContactNumber;
	
	
	
	@Size(max =200)
	@Column(name = "PERM_ADD1")
	private String permAdd1;
	
	@Size(max =200)
	@Column(name = "PERM_ADD2")
	private String permAdd2;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PERM_CITY_CODE",updatable = false)
	private City city2;
	
//	@OneToOne
//	@JoinColumn(name="PERM_COUNTRY_CODE",updatable = false)
//	private Country country2;
//	
//	@OneToOne
//	@JoinColumn(name="PERM_STATE_CODE",updatable = false)
//	private State state2;
	
	@Size(max =200)
	@Column(name = "PERM_CONTACT_NUMBER")
	private String permContactNumber;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="DEPARTMENT_CODE",updatable = false)
	private Department department;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="DESIGNATION_CODE",updatable = false)
	private Designation designation;
	
	
	
	
	@Size(max = 50)
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	
	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();
	@Size(max = 50)
	@Column(name = "UPD_BY", insertable = false)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false)
	private Date updDate;
	
	@JsonIgnore
	@OneToOne(mappedBy="employee", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private ArmsLicenses armLicense;
	
	@Column(name="PROFILE_IMAGE")
	private String profilePic;

	public Employee() {
		super();
		
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPayeeCode() {
		return payeeCode;
	}

	public void setPayeeCode(String payeeCode) {
		this.payeeCode = payeeCode;
	}

	public String getHomeDistrict() {
		return homeDistrict;
	}

	public void setHomeDistrict(String homeDistrict) {
		this.homeDistrict = homeDistrict;
	}

	public String getDepartmentCourt() {
		return departmentCourt;
	}

	public void setDepartmentCourt(String departmentCourt) {
		this.departmentCourt = departmentCourt;
	}

	public String getOrederNumber() {
		return orederNumber;
	}

	public void setOrederNumber(String orederNumber) {
		this.orederNumber = orederNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Date getDop() {
		return dop;
	}

	public void setDop(Date dop) {
		this.dop = dop;
	}

	public String getPlacePresentPosting() {
		return placePresentPosting;
	}

	public void setPlacePresentPosting(String placePresentPosting) {
		this.placePresentPosting = placePresentPosting;
	}

	public String getBatchYear() {
		return batchYear;
	}

	public void setBatchYear(String batchYear) {
		this.batchYear = batchYear;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}

	public Date getDor() {
		return dor;
	}

	public void setDor(Date dor) {
		this.dor = dor;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobileNumber1() {
		return mobileNumber1;
	}

	public void setMobileNumber1(String mobileNumber1) {
		this.mobileNumber1 = mobileNumber1;
	}

	public String getMobileNumber2() {
		return mobileNumber2;
	}

	public void setMobileNumber2(String mobileNumber2) {
		this.mobileNumber2 = mobileNumber2;
	}

	public String getCorrAdd1() {
		return corrAdd1;
	}

	public void setCorrAdd1(String corrAdd1) {
		this.corrAdd1 = corrAdd1;
	}

	public String getCorrAdd2() {
		return corrAdd2;
	}

	public void setCorrAdd2(String corrAdd2) {
		this.corrAdd2 = corrAdd2;
	}

	public City getCity1() {
		return city1;
	}

	public void setCity1(City city1) {
		this.city1 = city1;
	}

	public String getCorrContactNumber() {
		return corrContactNumber;
	}

	public void setCorrContactNumber(String corrContactNumber) {
		this.corrContactNumber = corrContactNumber;
	}

	public String getPermAdd1() {
		return permAdd1;
	}

	public void setPermAdd1(String permAdd1) {
		this.permAdd1 = permAdd1;
	}

	public String getPermAdd2() {
		return permAdd2;
	}

	public void setPermAdd2(String permAdd2) {
		this.permAdd2 = permAdd2;
	}

	public City getCity2() {
		return city2;
	}

	public void setCity2(City city2) {
		this.city2 = city2;
	}

	public String getPermContactNumber() {
		return permContactNumber;
	}

	public void setPermContactNumber(String permContactNumber) {
		this.permContactNumber = permContactNumber;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
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

	public ArmsLicenses getArmLicense() {
		return armLicense;
	}

	public void setArmLicense(ArmsLicenses armLicense) {
		this.armLicense = armLicense;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	

}
