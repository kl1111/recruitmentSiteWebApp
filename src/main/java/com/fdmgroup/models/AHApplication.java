package com.fdmgroup.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class AHApplication {

	@Id
	@SequenceGenerator(name = "AHApplication_id_seq", sequenceName = "AHApplication_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AHApplication_id_seq")
	private Integer applicationId;
	@ManyToOne
	@JoinColumn(name = "USERID")
	private AHUser ahUser;
	@ManyToOne
	@JoinColumn(name = "JOBID")
	private AHJob ahJob;
	private Date dateApplied;
	@Enumerated(EnumType.STRING)
	private AHStatus applicationStatus;

	public AHApplication() {}
	
	public AHApplication(Date dateApplied, 
			AHStatus applicationStatus) {
		this.dateApplied = dateApplied;
		this.applicationStatus = applicationStatus;
	}
	
	public AHApplication(AHUser ahUser, AHJob ahJob, Date dateApplied, AHStatus applicationStatus){
		this.ahUser = ahUser;
		this.ahJob = ahJob;
		this.dateApplied = dateApplied;
		this.applicationStatus = applicationStatus;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public AHUser getUser() {
		return ahUser;
	}

	public AHJob getJob() {
		return ahJob;
	}

	public Date getDateApplied() {
		return dateApplied;
	}

	public AHStatus getApplicationStatus() {
		return applicationStatus;
	}
	
	public void setApplicationStatus(AHStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public void setUser(AHUser ahUser){
		this.ahUser = ahUser;
	}
	
	public void setJob(AHJob ahJob){
		this.ahJob = ahJob;
	}
	
}
