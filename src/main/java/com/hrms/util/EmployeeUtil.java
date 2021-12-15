package com.hrms.util;

import java.util.Date;


import javax.persistence.Lob;


public class EmployeeUtil {
    private String empCode;
    
	private String empName;
	
	private String departmentCode;
	private String categoryCode;
	private String categoryName;
	private String designationCode;
	private String departmentName;
	private String designationName;
	
	
	
	private String batchYear;
	
	private Date dateOfJoining;
	
	private Date dateOfPosting;
	
	private Date dateOfRetirement;


	@Lob
	private byte[] imageProfile;
	
	private String   employeePayeeCode;
	
	
	
	private String presentPosting;
	
	private String suspention;
	
	private String  typeCourtDepartment;
	
	private String vigilanceQuery;
	
	private String vrs;
	
	
	
	
	// STEP-1======================end....
	
	private String aadharNo;
	private String addCharge;
	private String email;
	
	private String gender;
	private String martialStatus;
	private String telephone;
	
	private String onAdditionalCharge;
	private Date orderDate;
	private String orderNo;
	
	private String panNo;
	private String pinCode;
	private String qualification;
	private String transfer;
	private String uan;
	private String underRule7;
	private String underRule8;
	//===============
	private String cityCode;
	private String stateCode;
	private String countryCode;
	//=====================
	private String mobileNumber1;
	private String mobileNumber2;
    private String address1;
    private String address2;

  //=====================22
    
    private String promotion;
    private String acp;
    private String apr;
    private String acr;
    private String training;
    private String ltc;
    private String leaveAccount;
    private String empAwards;
    private String onDeputation;
    private String empDeputation;
    private String previousPostings;
    private String expired;
    private Date empDob;
    
    
	// STEP-2==========Arms_license_details============end....
	
    private String armsCode;
	private String name;
	private String fatherName;
	private String addressArms;
	
	private String  district;
	private String state;
	private String armsArea;
	
	private Date doi;
	private Date dov;
	private String toa;
	
	private String top;
	private int armsNol;
	private String lcd;
	
	private String dealerDetails;
	
	
	
	public EmployeeUtil(String empCode2, String armsCode2, String empName2,  String categoryName2, String deptName,
		String desgName, byte[] imageProfile2) {
		this.empCode = empCode2;
		this.armsCode=armsCode2;
		this.empName=empName2;
		this.categoryName=categoryName2;
		this.departmentName=deptName;
		this.designationName=desgName;
		this.imageProfile=imageProfile2;
		
		
	}
	
	public EmployeeUtil(Date dateOfJoining2, Date empDob2, Date dateOfRetirement2, String presentPosting2) {
		this.dateOfJoining = dateOfJoining2;
		this.empDob = empDob2;
		this.dateOfRetirement = dateOfRetirement2;
		this.presentPosting=presentPosting2;
		
	}

	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
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
	public String getBatchYear() {
		return batchYear;
	}
	public void setBatchYear(String batchYear) {
		this.batchYear = batchYear;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public Date getDateOfPosting() {
		return dateOfPosting;
	}
	public void setDateOfPosting(Date dateOfPosting) {
		this.dateOfPosting = dateOfPosting;
	}
	public Date getDateOfRetirement() {
		return dateOfRetirement;
	}
	public void setDateOfRetirement(Date dateOfRetirement) {
		this.dateOfRetirement = dateOfRetirement;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}
	public byte[] getImageProfile() {
		return imageProfile;
	}
	public void setImageProfile(byte[] imageProfile) {
		this.imageProfile = imageProfile;
	}
	public String getEmployeePayeeCode() {
		return employeePayeeCode;
	}
	public void setEmployeePayeeCode(String employeePayeeCode) {
		this.employeePayeeCode = employeePayeeCode;
	}

	
	public String getPresentPosting() {
		return presentPosting;
	}
	public void setPresentPosting(String presentPosting) {
		this.presentPosting = presentPosting;
	}
	public String getSuspention() {
		return suspention;
	}
	public void setSuspention(String suspention) {
		this.suspention = suspention;
	}
	public String getTypeCourtDepartment() {
		return typeCourtDepartment;
	}
	public void setTypeCourtDepartment(String typeCourtDepartment) {
		this.typeCourtDepartment = typeCourtDepartment;
	}
	public String getVigilanceQuery() {
		return vigilanceQuery;
	}
	public void setVigilanceQuery(String vigilanceQuery) {
		this.vigilanceQuery = vigilanceQuery;
	}
	public String getVrs() {
		return vrs;
	}
	public void setVrs(String vrs) {
		this.vrs = vrs;
	}
	//===============================================
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getAddCharge() {
		return addCharge;
	}
	public void setAddCharge(String addCharge) {
		this.addCharge = addCharge;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getOnAdditionalCharge() {
		return onAdditionalCharge;
	}
	public void setOnAdditionalCharge(String onAdditionalCharge) {
		this.onAdditionalCharge = onAdditionalCharge;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	public String getUan() {
		return uan;
	}
	public void setUan(String uan) {
		this.uan = uan;
	}
	
	public String getTransfer() {
		return transfer;
	}
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	public String getUnderRule7() {
		return underRule7;
	}
	public void setUnderRule7(String underRule7) {
		this.underRule7 = underRule7;
	}
	public String getUnderRule8() {
		return underRule8;
	}
	public void setUnderRule8(String underRule8) {
		this.underRule8 = underRule8;
	}
	//==========================================
	
	
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getArmsArea() {
		return armsArea;
	}
	public void setArmsArea(String armsArea) {
		this.armsArea = armsArea;
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
	
	public int getArmsNol() {
		return armsNol;
	}
	public void setArmsNol(int armsNol) {
		this.armsNol = armsNol;
	}
	public String getLcd() {
		return lcd;
	}
	public void setLcd(String lcd) {
		this.lcd = lcd;
	}
	public String getDealerDetails() {
		return dealerDetails;
	}
	public void setDealerDetails(String dealerDetails) {
		this.dealerDetails = dealerDetails;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public String getAcp() {
		return acp;
	}
	public void setAcp(String acp) {
		this.acp = acp;
	}
	public String getApr() {
		return apr;
	}
	public void setApr(String apr) {
		this.apr = apr;
	}
	public String getAcr() {
		return acr;
	}
	public void setAcr(String acr) {
		this.acr = acr;
	}
	public String getTraining() {
		return training;
	}
	public void setTraining(String training) {
		this.training = training;
	}
	public String getLtc() {
		return ltc;
	}
	public void setLtc(String ltc) {
		this.ltc = ltc;
	}
	public String getLeaveAccount() {
		return leaveAccount;
	}
	public void setLeaveAccount(String leaveAccount) {
		this.leaveAccount = leaveAccount;
	}
	public String getEmpAwards() {
		return empAwards;
	}
	public void setEmpAwards(String empAwards) {
		this.empAwards = empAwards;
	}
	public String getOnDeputation() {
		return onDeputation;
	}
	public void setOnDeputation(String onDeputation) {
		this.onDeputation = onDeputation;
	}
	public String getEmpDeputation() {
		return empDeputation;
	}
	public void setEmpDeputation(String empDeputation) {
		this.empDeputation = empDeputation;
	}
	public String getPreviousPostings() {
		return previousPostings;
	}
	public void setPreviousPostings(String previousPostings) {
		this.previousPostings = previousPostings;
	}
	public String getExpired() {
		return expired;
	}
	public void setExpired(String expired) {
		this.expired = expired;
	}
	public Date getEmpDob() {
		return empDob;
	}
	public void setEmpDob(Date empDob) {
		this.empDob = empDob;
	}

	public EmployeeUtil() {
		super();
	}
	
	
}
