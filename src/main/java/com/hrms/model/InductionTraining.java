package com.hrms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "INDUCT_TRAINING_MAST")
public class InductionTraining implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3128717299783557771L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INDUCTION_ID")
	private long idMast;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_CODE",updatable = false)
	private Department deptCode;
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();
	
	@OneToMany(mappedBy="idMast",cascade = CascadeType.ALL,orphanRemoval = true)
	List<InductionTrainingDetail> inductionTrainingDetail;

	
	public List<InductionTrainingDetail> getInductionTrainingDetail() {
		return inductionTrainingDetail;
	}
	public void setInductionTrainingDetail(List<InductionTrainingDetail> inductionTrainingDetail) {
		this.inductionTrainingDetail=inductionTrainingDetail;
	}

	public long getIdMast() {
		return idMast;
	}
	public void setIdMast(long idMast) {
		this.idMast = idMast;
	}
	public Employee getEmpCode() {
		return empCode;
	}
	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}
	public Department getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(Department deptCode) {
		this.deptCode = deptCode;
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
	
	public InductionTraining(long idMast, Employee empCode, Department deptCode, String insBy, Date insDate,
			List<InductionTrainingDetail> inductionTrainingDetail) {
		super();
		this.idMast = idMast;
		this.empCode = empCode;
		this.deptCode = deptCode;
		this.insBy = insBy;
		this.insDate = insDate;
		this.inductionTrainingDetail = inductionTrainingDetail;
	}
	public InductionTraining() {
		super();
	}
	
	
}
