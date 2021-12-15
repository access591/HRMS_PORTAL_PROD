package com.hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name="TRAINING_SCHEDULE")
public class TrainingSchedule {
	
	@Id
	@Column(name="TR_SCH_CODE",updatable=false)
	private String trScheduleCode;
	
	@Temporal(TemporalType.DATE)
	@Column(name="TR_SCH_DATE")
	private Date trScheduleDate;
	
	@Size(max=50)
	@Column(name="TPOIC_SERIAL_NO")
	private String topicSerialNo;
	
	
	@Size(max=50)
	@Column(name="TRAINER_CODE",updatable=false)
	private String trainerCode;
	
	@Size(max=50)
	@Column(name="TRAINER")
	private String trainer;
	
	@Column(name="DATE_TO")
	private Date dateTo;
	
	@Column(name="DATE_FROM")
	private Date dateFrom;
	
	@Size(max=50)
	@Column(name="TRAINER_AGENCY")
	private String trainerAgency;
	
	@Size(max=50)
	@Column(name="FY_CODE")
	private String fyCode;
	
	@ManyToOne
	@JoinColumn(name="TR_REQ_CODE")
	private TrainingRequisition trainingRequisition;
	
	@Column(name="tr_req_Date")
	private Date trReqDate;

	public TrainingSchedule() {
		super();
	}

	public String getTrScheduleCode() {
		return trScheduleCode;
	}

	public void setTrScheduleCode(String trScheduleCode) {
		this.trScheduleCode = trScheduleCode;
	}

	public Date getTrScheduleDate() {
		return trScheduleDate;
	}

	public void setTrScheduleDate(Date trScheduleDate) {
		this.trScheduleDate = trScheduleDate;
	}

	public String getTopicSerialNo() {
		return topicSerialNo;
	}

	public void setTopicSerialNo(String topicSerialNo) {
		this.topicSerialNo = topicSerialNo;
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

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getTrainerAgency() {
		return trainerAgency;
	}

	public void setTrainerAgency(String trainerAgency) {
		this.trainerAgency = trainerAgency;
	}

	public String getFyCode() {
		return fyCode;
	}

	public void setFyCode(String fyCode) {
		this.fyCode = fyCode;
	}

	public TrainingRequisition getTrainingRequisition() {
		return trainingRequisition;
	}

	public void setTrainingRequisition(TrainingRequisition trainingRequisition) {
		this.trainingRequisition = trainingRequisition;
	}

	public Date getTrReqDate() {
		return trReqDate;
	}

	public void setTrReqDate(Date trReqDate) {
		this.trReqDate = trReqDate;
	}
	
	
	
	

}
