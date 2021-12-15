package com.hrms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "TR_REQUI_EMP_DETAIL")
public class TrainingReqEmployeeDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)   
	@Column(name="TR_REQEMP_ID")
	private Long reqEmpId;
	
	@Column(name = "TOPIC_SRL_NO")
	private String topicSrlNo;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TR_REQ_CODE",updatable = false)
	private TrainingRequisition trainingRequisition;

	public TrainingReqEmployeeDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getReqEmpId() {
		return reqEmpId;
	}

	public void setReqEmpId(Long reqEmpId) {
		this.reqEmpId = reqEmpId;
	}

	public String getTopicSrlNo() {
		return topicSrlNo;
	}

	public void setTopicSrlNo(String topicSrlNo) {
		this.topicSrlNo = topicSrlNo;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public TrainingRequisition getTrainingRequisition() {
		return trainingRequisition;
	}

	public void setTrainingRequisition(TrainingRequisition trainingRequisition) {
		this.trainingRequisition = trainingRequisition;
	}
	
	
	

}
