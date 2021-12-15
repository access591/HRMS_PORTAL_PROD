package com.hrms.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name ="M_Grade")
public class Grade implements Serializable
{
	/**
	 * @author Access surendra
	 */
	private static final long serialVersionUID = 633323013185969173L;

	  @Id
	  @Size(max =15)
		@Column(name = "GRADE_CODE")
		private String gradeCode;
	     @Size(max =50)
		@Column(name = "GARDE_NAME")
		private String gardeName;
	     @Size(max =50)
	    @Column(name = "INS_BY",updatable = false)
		private String insBy;
		
		@Column(name = "INS_DATE",updatable = false)
		private Date insDate =new Date();
		@Size(max =50)
		@Column(name = "UPD_BY",insertable = false)
		private String updBy;
		
		@Column(name = "UPD_DATE",insertable = false)
		private Date  updDate = new Date();
		
		@Size(max =1)
		@Column(name = "ACTIVE_YN")
		private String active;
		
	
		public String getGradeCode() {
			return gradeCode;
		}
		public void setGradeCode(String gradeCode) {
			this.gradeCode = gradeCode;
		}
		public String getGardeName() {
			return gardeName;
		}
		public void setGardeName(String gardeName) {
			this.gardeName = gardeName.substring(0, 1).toUpperCase()+ gardeName.substring(1);
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
		public String getActive() {
			return active;
		}
		public void setActive(String active) {
			this.active = active;
		}
		
	
}