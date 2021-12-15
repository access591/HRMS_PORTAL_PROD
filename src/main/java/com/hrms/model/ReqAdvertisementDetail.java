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
import javax.validation.constraints.Size;

@Entity
@Table(name="EMPREQ_ADVERTISEMENT_DETAILS")
public class ReqAdvertisementDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ADVRTDETAIL_ID")
	private Long reqAdvrtDetailId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADVRT_CODE")
	private ReqAdvertisement reqAdvertisement;
	
	@Column(name="ADVRT_DATE")
	private Date advtDate;
	
	@Size(max=50)
	@Column(name="MEDIA_TYPE")
	private String mediaType;
	
	@Size(max=50)
	@Column(name="MEDIA_NAME")
	private String mediaName;
	
	@Size(max=250)
	@Column(name="ADVT_AMOUNT")
	private String advtAmount;
	
	@Size(max=250)
	@Column(name="REMARKS")
	private String remarks;
	
	@ManyToOne
	@JoinColumn(name="REQ_CODE")
	private EmployeeRequisition employeeRequisition;
	
	@Column(name="REQ_DATE")
	private String reqDate;

	public Long getReqAdvrtDetailId() {
		return reqAdvrtDetailId;
	}

	public void setReqAdvrtDetailId(Long reqAdvrtDetailId) {
		this.reqAdvrtDetailId = reqAdvrtDetailId;
	}

	public ReqAdvertisement getReqAdvertisement() {
		return reqAdvertisement;
	}

	public void setReqAdvertisement(ReqAdvertisement reqAdvertisement) {
		this.reqAdvertisement = reqAdvertisement;
	}

	public Date getAdvtDate() {
		return advtDate;
	}

	public void setAdvtDate(Date advtDate) {
		this.advtDate = advtDate;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getAdvtAmount() {
		return advtAmount;
	}

	public void setAdvtAmount(String advtAmount) {
		this.advtAmount = advtAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	

	public EmployeeRequisition getEmployeeRequisition() {
		return employeeRequisition;
	}

	public void setEmployeeRequisition(EmployeeRequisition employeeRequisition) {
		this.employeeRequisition = employeeRequisition;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	
}
