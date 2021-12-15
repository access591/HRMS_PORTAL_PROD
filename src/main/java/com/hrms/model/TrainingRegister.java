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
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "TR_REGISTER_MASTER")
public class TrainingRegister implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6960632326976412838L;
	
	@Id
	@Column(name = "TR_REG_CODE")
	private String trRegCode;

	@Column(name = "TR_REG_DATE")
	private Date trRegDate;
	
	@ManyToOne
	@JoinColumn(name = "TR_SCH_CODE")
	private TrainingSchedule trScheduleCode;
	
	
	@Column(name = "TR_SCH_DATE")
	private Date trSchDate;
	
	
	@Column(name = "TOPIC_SRL_NO")
	private String topicSrlNo;
	
	@Column(name = "TR_TIME_FROM")
	private String  trTimeFrom;
	
	@Column(name = "TR_TIME__TO")
	private String trTimeTO;
	
	
	@Column(name = "TRAINER_CODE")
	private String trainerCode;
	   
	
	@Column(name = "TRAINER")
	private String trainer;
	
	@Column(name = "TR_FEEDBACK")
	private String trFeedback; 
	
	
	@Column(name = "HOD_CODE")
	private String hodCode;       
	  
	@Column(name = "HOD_FEEDBACK")
	private String hodFeedback;       
	      
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();
	
	@Column(name = "UPD_BY", insertable = false)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false)
	private Date updDate = new Date();
	
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="TR_DETAIL_ID")
TrainingRegisterDetails trainingRegisterDetails;

	
	
	public String getTrRegCode() {
		return trRegCode;
	}

	public void setTrRegCode(String trRegCode) {
		this.trRegCode = trRegCode;
	}

	public Date getTrRegDate() {
		return trRegDate;
	}

	public void setTrRegDate(Date trRegDate) {
		this.trRegDate = trRegDate;
	}

	
	public TrainingSchedule getTrScheduleCode() {
		return trScheduleCode;
	}

	public void setTrScheduleCode(TrainingSchedule trScheduleCode) {
		this.trScheduleCode = trScheduleCode;
	}

	public Date getTrSchDate() {
		return trSchDate;
	}

	public void setTrSchDate(Date trSchDate) {
		this.trSchDate = trSchDate;
	}

	public String getTopicSrlNo() {
		return topicSrlNo;
	}

	public void setTopicSrlNo(String topicSrlNo) {
		this.topicSrlNo = topicSrlNo;
	}



	public String getTrTimeFrom() {
		return trTimeFrom;
	}

	public void setTrTimeFrom(String trTimeFrom) {
		this.trTimeFrom = trTimeFrom;
	}

	public String getTrTimeTO() {
		return trTimeTO;
	}

	public void setTrTimeTO(String trTimeTO) {
		this.trTimeTO = trTimeTO;
	}

	public String getTrainerCode() {
		return trainerCode;
	}

	public void setTrainerCode(String trainerCode) {
		this.trainerCode = trainerCode;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getTrFeedback() {
		return trFeedback;
	}

	public void setTrFeedback(String trFeedback) {
		this.trFeedback = trFeedback;
	}

	public String getHodCode() {
		return hodCode;
	}

	public void setHodCode(String hodCode) {
		this.hodCode = hodCode;
	}

	public String getHodFeedback() {
		return hodFeedback;
	}

	public void setHodFeedback(String hodFeedback) {
		this.hodFeedback = hodFeedback;
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

	public TrainingRegisterDetails getTrainingRegisterDetails() {
		return trainingRegisterDetails;
	}

	public void setTrainingRegisterDetails(TrainingRegisterDetails trainingRegisterDetails) {
		this.trainingRegisterDetails = trainingRegisterDetails;
	}




	

}
