package com.hrms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "EMP_LOCAL_RMBSMT")
public class LocalConvyence implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6235454307588629360L;
	@Id
	@Column(name = "LOCAL_CONV_ID")
	private String localConvId;
	@Temporal(TemporalType.DATE)
	@Column(name ="LOCAL_CONV_DT")
	private Date localConvDate;
	
	@Column(name ="TOTAL_CLAIM",updatable = false)
	private int totalClaim;
	
	@Column(name ="TOTAL_PASS",updatable = false)
	private int totalPas;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	
	@Column(name = "APPROVED_BY")
	private String  approvedBy ; 
	
    @Column(name = "APPROVAL_STATUS")
	private String  approvalStatus ;
    
	@Column(name = "APPROVAL_DATE")
	private Date approvalDate ;
	
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();
	
	@Column(name = "FILL_DATE", updatable = false)
	private Date filDate = new Date();
	
	@OneToMany(mappedBy="localConvId",cascade = CascadeType.ALL,orphanRemoval = true)
	List<LocalConvyenceDetail> localConvyenceDetail;

	public String getLocalConvId() {
		return localConvId;
	}

	public void setLocalConvId(String localConvId) {
		this.localConvId = localConvId;
	}

	public Date getLocalConvDate() {
		return localConvDate;
	}

	public void setLocalConvDate(Date localConvDate) {
		this.localConvDate = localConvDate;
	}

	public int getTotalClaim() {
		return totalClaim;
	}

	public void setTotalClaim(int totalClaim) {
		this.totalClaim = totalClaim;
	}

	public int getTotalPas() {
		return totalPas;
	}

	public void setTotalPas(int totalPas) {
		this.totalPas = totalPas;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
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

	public Date getFilDate() {
		return filDate;
	}

	public void setFilDate(Date filDate) {
		this.filDate = filDate;
	}

	public List<LocalConvyenceDetail> getLocalConvyenceDetail() {
		return localConvyenceDetail;
	}

	public void setLocalConvyenceDetail(List<LocalConvyenceDetail> localConvyenceDetail) {
		this.localConvyenceDetail = localConvyenceDetail;
	}

	public LocalConvyence(String localConvId, Date localConvDate, int totalClaim, int totalPas, Employee empCode,
			String approvedBy, String approvalStatus, Date approvalDate, String insBy, Date insDate, Date filDate,
			List<LocalConvyenceDetail> localConvyenceDetail) {
		super();
		this.localConvId = localConvId;
		this.localConvDate = localConvDate;
		this.totalClaim = totalClaim;
		this.totalPas = totalPas;
		this.empCode = empCode;
		this.approvedBy = approvedBy;
		this.approvalStatus = approvalStatus;
		this.approvalDate = approvalDate;
		this.insBy = insBy;
		this.insDate = insDate;
		this.filDate = filDate;
		this.localConvyenceDetail = localConvyenceDetail;
	}

	public LocalConvyence() {
		super();
	}
	
	
	

			
}
