package com.hrms.model;

import java.io.Serializable;
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
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(name = "T_EMP_PROM_DTLS")
public class EmployeePromotion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6153243912435691831L;


	/**
	 * Auth Surendra
	 */

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="EPD")
	private long id;
	
	
	@Column(name = "EDP_MEMO_NO")
	private String edpMemoNo;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PROMOTION_DATE")
	private Date promotionDate;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "OLD_POST")
	private String oldPost;
	
	@Column(name = "new_POST")
	private String newPost;

	@Column(name = "EMP_UPLOAD__DOC", nullable = true)
	private String empUploadDoc;
	@Size(max = 50)
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	@Size(max = 50)
	@Column(name = "UPD_BY", insertable = false)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false)
	private Date  updDate = new Date();



	public String getEdpMemoNo() {
		return edpMemoNo;
	}

	public void setEdpMemoNo(String edpMemoNo) {
		this.edpMemoNo = edpMemoNo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public Date getPromotionDate() {
		return promotionDate;
	}

	public void setPromotionDate(Date promotionDate) {
		this.promotionDate = promotionDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOldPost() {
		return oldPost;
	}

	public void setOldPost(String oldPost) {
		this.oldPost = oldPost;
	}

	public String getNewPost() {
		return newPost;
	}

	public void setNewPost(String newPost) {
		this.newPost = newPost;
	}

	public String getEmpUploadDoc() {
		return empUploadDoc;
	}

	public void setEmpUploadDoc(String empUploadDoc) {
		this.empUploadDoc = empUploadDoc;
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
