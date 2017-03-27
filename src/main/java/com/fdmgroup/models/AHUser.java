package com.fdmgroup.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


/**
* The User class for the AssassinForHire Project
*
* @author  Jerry Wang
* @version 1.0
* @since   2017-01-03 
*/

@Entity
@NamedQuery(query = "Select e from AHUser e where e.name = :name", name = "find user by name")
public class AHUser {
	@Id
	@SequenceGenerator(name = "usersSeq", sequenceName = "AHUser_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersSeq")
	
	@Column(name = "userId")
	private int userId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")  @Enumerated(EnumType.STRING)
	private AHUserType role;


	@Column(name= "userStatus") @Enumerated(EnumType.STRING)
	private AHStatus userStatus;

	@OneToOne(mappedBy = "ahUser", orphanRemoval=true)
	private AHBio ahBio;
	
	@OneToMany(mappedBy = "ahUser", cascade = CascadeType.ALL)
	private List<AHApplication> ahApplications;
	
	@OneToMany(mappedBy = "ahUser", cascade = CascadeType.ALL)
	private List<AHJob> ahJobs;
	
	public AHUser(){
		
	}
	
	public AHUser(String name, String email, String password, AHUserType role, AHStatus userStatus) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.userStatus = userStatus;
		this.ahApplications = new ArrayList<AHApplication>();
		this.ahJobs = new ArrayList<AHJob>();
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AHUserType getRole() {
		return role;
	}

	public void setRole(AHUserType role) {
		this.role = role;
	}

	public AHStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(AHStatus userStatus) {
		this.userStatus = userStatus;
	}
	
	public void setBio(AHBio ahBio) {
		this.ahBio = ahBio;
	}
	
	public AHBio getBio() {
		return ahBio;
	}
	
	public void addApplication(AHApplication ahApplication){
		ahApplications.add(ahApplication);
	}
	
	public List<AHApplication> getApplications(){
		return ahApplications;
	}
	
	public void addJob(AHJob ahJob){
		ahJobs.add(ahJob);
	}
	
	public List<AHJob> getJobs(){
		return ahJobs;
	}
}
