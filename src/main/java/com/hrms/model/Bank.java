/**
 * 
 */
package com.hrms.model;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author Access
 *
 */
@Entity
@Table(name = "M_BANK")

public class Bank implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1785482366741143946L;

	/**
	 * 
	 */
	

	/**
	 * 
	 */
	

	/**
	 * Mohit Access 
	 */
	

	@Id
	@Size(max =15)
	@Column(name = "BANK_CODE")
	private String bankCode;
	@Size(max =100)
	@Column(name = "BANK_NAME")
	private String bankName;
	@Size(max =100)
	@Column(name = "BANK_ADD")
	private String bankAdd;
	@Size(max =100)
	@Column(name = "BANK_ADDR2")
	private String bankAddr2;

	@ManyToOne
	@JoinColumn(name = "CITY_CODE",updatable = false)
	private City cityCode;

	@ManyToOne
	@JoinColumn(name = "STATE_CODE",updatable = false)
	private State stateCode;

	@ManyToOne
	@JoinColumn(name = "COUNTRY_CODE" ,updatable = false)
	private Country countryCode;
	@Size(max =16)
	@Column(name = "ACCOUNT_NO")
	private String accountNo;
	@Size(max =20)
	@Column(name = "IFSC_CODE")
	private String ifscCode;
	@Size(max =25)
	@Column(name = "SWIFT_CODE")
	private String swiftCode;
	@Size(max =12)
	@Column(name = "TELEPHONE_NO")
	private String telephoneNo;
	@Size(max =11)
	@Column(name = "MOBILE_NO")
	private String mobileNo;
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
	
	

	@Size(max =1)
	@Column(name = "ACTIVE_YN")
	private String active;

	



	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName.toUpperCase();
	}

	public String getBankAdd() {
		return bankAdd;
	}

	public void setBankAdd(String bankAdd) {
		this.bankAdd = bankAdd;
	}

	public String getBankAddr2() {
		return bankAddr2;
	}

	public void setBankAddr2(String bankAddr2) {
		this.bankAddr2 = bankAddr2;
	}

	public City getCityCode() {
		return cityCode;
	}

	public void setCityCode(City cityCode) {
		this.cityCode = cityCode;
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

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	