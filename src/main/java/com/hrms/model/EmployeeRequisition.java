package com.hrms.model;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
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

@Entity
@Table(name = "EMPREQUISITION_MASTER")
public class EmployeeRequisition implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 8223555113108625254L;

	@Id
	@Size(max = 200)
	@Column(name = "REQ_CODE", updatable = false)
	private String reqCode;

	@Column(name = "REQ_DATE")
	@Temporal(TemporalType.DATE)
	private Date reqDate;

	@ManyToOne
	@JoinColumn(name = "DEPT_CODE")
	private Department departmet;

	@Size(max = 40)
	@Column(name = "REQ_PRIORITY")
	private String reqPriority;

	@Size(max = 50)
	@Column(name = "REQ_APPROVER", length = 15)
	private String reqApprover;

	@Column(name = "REMARKS", length = 300)
	private String remarks;

	@Column(name = "INS_BY", length = 15)
	private String insBy;

	@Column(name = "INS_DATE")
	@Temporal(TemporalType.DATE)
	private Date insDate;

	@Column(name = "REQ_TILL")
	@Temporal(TemporalType.DATE)
	private Date reqTill;

	@Column(name = "APPROVE_DATE")
	@Temporal(TemporalType.DATE)
	private Date approveDate;

	@Size(max = 20)
	@Column(name = "STATUS")
	private String status = "N";

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "employeeRequisition", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmployeeRequisitionDetail> employeRequisitionDetail;

	 @JsonBackReference
	public List<EmployeeRequisitionDetail> getEmployeRequisitionDetail() {
		return employeRequisitionDetail;
	}

	public void setEmployeRequisitionDetail(List<EmployeeRequisitionDetail> employeRequisitionDetail) {
		this.employeRequisitionDetail = employeRequisitionDetail;
	}

	public String getReqCode() {
		return reqCode;
	}

	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}

	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	public Department getDepartmet() {
		return departmet;
	}

	public void setDepartmet(Department departmet) {
		this.departmet = departmet;
	}

	public String getReqPriority() {
		return reqPriority;
	}

	public void setReqPriority(String reqPriority) {
		this.reqPriority = reqPriority;
	}

	public String getReqApprover() {
		return reqApprover;
	}

	public void setReqApprover(String reqApprover) {
		this.reqApprover = reqApprover;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Date getReqTill() {
		return reqTill;
	}

	public void setReqTill(Date reqTill) {
		this.reqTill = reqTill;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}