package com.hrms.model;

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

@Entity
@Table(name = "M_ARMS_LICENSES_DETAIL")
public class ArmsLicensesDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARMS_LICENSE_DETAIL_CODE")
	private int armsLicenseCode;

	@Column(name = "ARMS_LICENSE_DETAIL")
	private String armsLicenseNumber;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "LICENSE_DATEOF_ISSUE")
	private Date dateOfIssue;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "LICENSE_DATEOF_EXPIRY")
	private Date dateOfExpire;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ARMS_LICENSES_CODE",nullable=false)
	private ArmsLicenses armsLicenses;

	public ArmsLicensesDetail() {
		super();
		
	}

	public int getArmsLicenseCode() {
		return armsLicenseCode;
	}

	public void setArmsLicenseCode(int armsLicenseCode) {
		this.armsLicenseCode = armsLicenseCode;
	}

	public String getArmsLicenseNumber() {
		return armsLicenseNumber;
	}

	public void setArmsLicenseNumber(String armsLicenseNumber) {
		this.armsLicenseNumber = armsLicenseNumber;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public Date getDateOfExpire() {
		return dateOfExpire;
	}

	public void setDateOfExpire(Date dateOfExpire) {
		this.dateOfExpire = dateOfExpire;
	}

	public ArmsLicenses getArmsLicenses() {
		return armsLicenses;
	}

	public void setArmsLicenses(ArmsLicenses armsLicenses) {
		this.armsLicenses = armsLicenses;
	}
	
	

}
