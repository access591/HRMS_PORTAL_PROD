package com.hrms.model;

import java.io.Serializable;

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
@Table(name = "TR_REGISTER_DETAIL")
public class TrainingRegisterDetails implements Serializable {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = -6290645706436649061L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TR_DETAIL_ID")
	private  long  id;
	
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee  employee;
	
	
	@Column(name = "EMP_FEEDBACK")
	private  String  empFeedback;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public String getEmpFeedback() {
		return empFeedback;
	}


	public void setEmpFeedback(String empFeedback) {
		this.empFeedback = empFeedback;
	}
	
	
	  
	
}
