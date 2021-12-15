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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "INDUCT_TRAINING_DET")
public class InductionTrainingDetail  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2614057709105003896L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IN_DETAIL_ID_DET")
	private long indId;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_CODE",updatable = false)
	private Department deptCode;
	
	
	@Column(name = "CONT_PERSON")
	private String contPerson;
	@Temporal(TemporalType.DATE)
	@Column(name = "TRAINING_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date trainingDate;
	@ManyToOne
	@JoinColumn(name = "idMast",updatable=false)
	private InductionTraining  idMast;

	@Column(name = "DURATION")
	private String duration;

	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();


	public long getIndId() {
		return indId;
	}

	public void setIndId(long indId) {
		this.indId = indId;
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

	public String getContPerson() {
		return contPerson;
	}

	public void setContPerson(String contPerson) {
		this.contPerson = contPerson;
	}

	public Date getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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

	public InductionTraining getIdMast() {
		return idMast;
	}

	public void setIdMast(InductionTraining idMast) {
		this.idMast = idMast;
	}

}
