package com.fdmgroup.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class AHJob {

	@Id
	@SequenceGenerator(name = "AHJob_id_seq", sequenceName = "AHJob_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AHJob_id_seq")
	private Integer jobId;
	@ManyToOne
	@JoinColumn(name = "USERID")
	private AHUser ahUser;
	private Date datePosted;
	private String jobTitle;
	private String description;
	@Enumerated(EnumType.STRING)
	private AHStatus jobStatus;
	@OneToMany(mappedBy = "ahJob", cascade = CascadeType.ALL)
	private List<AHApplication> ahApplications;
	
	public AHJob() {}
	
	public AHJob(Date datePosted, String jobTitle, String description, AHStatus jobStatus) {
		this.datePosted = datePosted;
		this.jobTitle = jobTitle;
		this.description = description;
		this.jobStatus = jobStatus;
		this.ahApplications = new ArrayList<AHApplication>();
	}
	
	public AHJob(AHUser ahUser, Date datePosted, String jobTitle, String description, AHStatus jobStatus) {
		this.ahUser = ahUser;
		this.datePosted = datePosted;
		this.jobTitle = jobTitle;
		this.description = description;
		this.jobStatus = jobStatus;
	}
	
	
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Integer getJobId() {
		return jobId;
	}

	public AHUser getUser() {
		return ahUser;
	}
	
	public void setUser(AHUser ahUser){
		this.ahUser = ahUser;
	}

	public Date getDatePosted() {
		return datePosted;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}

	public String getDescription() {
		return description;
	}

	public AHStatus getJobStatus() {
		return jobStatus;
	}
	
	public void addApplication(AHApplication ahApplication){
		ahApplications.add(ahApplication);
	}
	
	public List<AHApplication> getApplications(){
		return ahApplications;
	}
}
