package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name ="CONTACT_US")
public class ContactUs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -221008883311364871L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cid;
	@Size(max = 50)
	@Column(name = "DESIGNATION")
	private String designation;
	@Size(max = 50)
	@Column(name = "EMAIL")
	private String email;
	@Size(max = 50)
	@Column(name = "NAME")
	private String name;
	@Size(max = 500)
	@Column(name = "O_ADDRESS")
	private String oAddress;
	@Size(max = 30)
	@Column(name = "O_EXT")
	private String oXit;
	@Size(max = 50)
	@Column(name = "PHONE_NO")
	private String phoneNo;
	@Size(max = 5)
	@Column(name = "ID")
	private String id;
	@Size(max = 200)
	@Column(name = "PIC")
	private String pic;
	@Size(max = 5)
	@Column(name = "P_ID")
	private String pId;
	@Size(max = 50)
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();
	@Size(max = 50)
	@Column(name = "UPD_BY", insertable = false)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false)
	private Date updDate = new Date();

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getoAddress() {
		return oAddress;
	}

	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}

	public String getoXit() {
		return oXit;
	}

	public void setoXit(String oXit) {
		this.oXit = oXit;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
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
