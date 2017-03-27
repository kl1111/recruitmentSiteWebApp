package com.fdmgroup.services;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.models.AHJob;
import com.fdmgroup.models.AHStatus;
import com.fdmgroup.models.AHUser;

public class AHJobService {
	
	private EntityManager entityManager;

	public AHJobService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public AHJob createJob(Date datePosted, String jobTitle, String description, AHStatus jobStatus) {
		AHJob ahJob = new AHJob(datePosted, jobTitle, description, jobStatus);
		entityManager.persist(ahJob);
		return ahJob;
	}
	
	public AHJob createJob(AHUser ahUser, Date datePosted, String jobTitle, String description, AHStatus jobStatus) {
		AHJob ahJob = new AHJob(ahUser, datePosted, jobTitle, description, jobStatus);
		entityManager.persist(ahJob);
		return ahJob;
	}

	public AHJob findJob(int jobId) {
		return entityManager.find(AHJob.class, jobId);
	}
	
	public List<AHJob> findJobsByJobTitle(String jobTitle) {
		TypedQuery<AHJob> query = 
				entityManager.createQuery("SELECT j from AHJob j WHERE UPPER(j.jobTitle) LIKE UPPER(CONCAT('%',:jobTitle,'%'))", 
						AHJob.class);
		query.setParameter("jobTitle", jobTitle);
		return query.getResultList();
	}

	public void updateJob(AHJob ahJob) {
		AHJob foundJob = findJob(ahJob.getJobId());
		if(foundJob != null) {
			foundJob = entityManager.merge(ahJob);
		}
	}

	public void deleteJob(Integer jobId) {
		AHJob foundJob = findJob(jobId);
		if(foundJob != null){
			entityManager.remove(foundJob);
		}
	}

	public List<AHJob> getAllActiveJobs(){
		TypedQuery<AHJob> query = 
				entityManager.createQuery("SELECT j from AHJob j WHERE j.jobStatus =:jobStatus", 
						AHJob.class);
		query.setParameter("jobStatus", AHStatus.ACTIVE);
		return query.getResultList();
	}

	
}
