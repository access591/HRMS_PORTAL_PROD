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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="T_ORDER_TRACKING")
public class OrderIssueTracking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ORDER_TR_ID",length = 6)
	private Long orderTrackingId;
	
	@Column(name="ORDER_TR_DATE")
	private Date orderTrackingDate;
	
	@Column(name="ORDER_NO",length = 200)
	private String orderNumber;
	
	@Column(name="ORDER_TITLE",length = 100)
	private String orderTitle;
	
	@Column(name="ORDER_DESC",length = 500)
	private String orderDescription;
	
	@Column(name="ORDER_FILE_NAME",length=100)
	private String orderFileName;
	
	@ManyToOne
	@JoinColumn(name="DEPT_CODE")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name="ISSUED_BY")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee employee;
	
	@Column(name="ISSUED_DATE")
	private Date issuedDate = new Date();
	
	@ManyToOne
	@JoinColumn(name="BRANCH_CODE")
	private BranchMaster branchMaster;
	
	
	@Column(name = "INS_BY", updatable = false,length = 50)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false,length = 6)
	private Date insDate = new Date();

	@Column(name = "UPD_BY", insertable = false,length = 50)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false,length = 6)
	private Date updDate = new Date();

	public OrderIssueTracking() {
		super();
		
	}

	public Long getOrderTrackingId() {
		return orderTrackingId;
	}

	public void setOrderTrackingId(Long orderTrackingId) {
		this.orderTrackingId = orderTrackingId;
	}

	public Date getOrderTrackingDate() {
		return orderTrackingDate;
	}

	public void setOrderTrackingDate(Date orderTrackingDate) {
		this.orderTrackingDate = orderTrackingDate;
	}

	

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public String getOrderFileName() {
		return orderFileName;
	}

	public void setOrderFileName(String orderFileName) {
		this.orderFileName = orderFileName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public BranchMaster getBranchMaster() {
		return branchMaster;
	}

	public void setBranchMaster(BranchMaster branchMaster) {
		this.branchMaster = branchMaster;
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
