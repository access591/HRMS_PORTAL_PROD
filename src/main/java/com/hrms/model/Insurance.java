package com.hrms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "M_INSURANCE")
public class Insurance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 236983739265533909L;

	/**
	 * 
	 */

@Id
@Size(max = 15)
@Column(name = "INS_CODE")
private String 	insCode;
@Size(max = 50)
@Column(name = "NAME")
private String name;
@Size(max =50)
@Column(name = "ADD1")
private String add1;
@Size(max =100)
@Column(name = "ADD2")
private String add2;
@Size(max = 15)
@Column(name = "CITY_CODE")
private String cityCode;
@Size(max = 50)
@Column(name = "CITY_DESC")
private String cityDesc;

@Column(name = "PHONE")
private String phoneNo;

@Column(name = "FAX")
private String fax;
@Size(max = 45)
@Column(name = "EMAIL")
private String email;
@Size(max =1)
@Column(name = "ACTIVE_YN")
private String active;
@Size(max =50)
@Column(name = "INS_BY",updatable = false)
private String insBy;

@Column(name = "INS_DATE",updatable = false)
private Date insDate =new Date();
@Size(max = 50)
@Column(name = "UPD_BY",insertable = false)
private String updBy;

@Column(name = "UPD_DATE",insertable = false)
private Date  updDate = new Date();

public String getInsCode() {
	return insCode;
}
public void setInsCode(String insCode) {
	this.insCode = insCode;
}



public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name.substring(0, 1).toUpperCase()+ name.substring(1);;
}
public String getAdd1() {
	return add1;
}
public void setAdd1(String add1) {
	this.add1 = add1;
}
public String getAdd2() {
	return add2;
}
public void setAdd2(String add2) {
	this.add2 = add2;
}
public String getCityCode() {
	return cityCode;
}
public void setCityCode(String cityCode) {
	this.cityCode = cityCode;
}
public String getCityDesc() {
	return cityDesc;
}
public void setCityDesc(String cityDesc) {
	this.cityDesc = cityDesc;
}

public String getPhoneNo() {
	return phoneNo;
}
public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}
public String getFax() {
	return fax;
}
public void setFax(String fax) {
	this.fax = fax;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
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

}
