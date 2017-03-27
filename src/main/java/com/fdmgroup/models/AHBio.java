package com.fdmgroup.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class AHBio {
	
	@Id
	@SequenceGenerator(name = "AHBio_id_seq", sequenceName = "AHBio_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AHBio_id_seq")
	private Integer bioId;
	@OneToOne(optional = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "USERID")
	private AHUser ahUser;
	private String bio;
	private String skills;
	
	public AHBio() {}
	
	public AHBio(AHUser ahUser, String bio, String skills) {
		this.ahUser = ahUser;
		this.bio = bio;
		this.skills = skills;
	}
	
	public void setBioId(Integer bioId) {
		this.bioId = bioId;
	}
	
	public Integer getBioId() {
		return bioId;
	}
	
	public AHUser getUser() {
		return ahUser;
	}
	
	public String getBio() {
		return bio;
	}
	
	public String getSkills() {
		return skills;
	}
}
