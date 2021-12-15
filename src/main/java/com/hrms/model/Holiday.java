package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name ="M_Holiday")
public class Holiday implements Serializable
{

	/**
	 * Access Surendra Kumar Meena
	 */
	private static final long serialVersionUID = 3252326016046684834L;
	 @Id
	    @Size(max =15)
		@Column(name = "HOLIDAY_CODE")
	    private String 	holidayCode;
	 @Size(max =40)
	    @Column(name = "DESCRIPTION")
	    private String  description;
	    
		@Temporal(TemporalType.DATE)
	    @Column(name ="DATE_OF_HOLIDAY")
	    private Date dateOfHoliday;
	    @Size(max =1)
	    @Column(name ="HOLIDAY_TYPE")
	    private String  holidayType;
	    @Size(max =1)
	    @Column(name ="ACTIVE")
	    private String active;
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
	    public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description.substring(0, 1).toUpperCase()+ description.substring(1);
		}			
		public String getActive() {
			return active;
		}

		public void setActive(String active) {
			this.active = active;
		}

		public String getHolidayCode() {
			return holidayCode;
		}

		public void setHolidayCode(String holidayCode) {
			this.holidayCode = holidayCode;
		}

		public Date getDateOfHoliday() {
			return dateOfHoliday;
		}

		public void setDateOfHoliday(Date dateOfHoliday) {
			this.dateOfHoliday = dateOfHoliday;
		}

		public String getHolidayType() {
			return holidayType;
		}

		public void setHolidayType(String holidayType) {
			this.holidayType = holidayType;
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
