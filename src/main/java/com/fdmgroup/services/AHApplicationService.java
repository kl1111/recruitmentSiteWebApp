package com.fdmgroup.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.fdmgroup.models.AHApplication;
import com.fdmgroup.models.AHJob;
import com.fdmgroup.models.AHStatus;
import com.fdmgroup.models.AHUser;

public class AHApplicationService {

	private EntityManager entityManager;

	public AHApplicationService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public AHApplication createApplication(Date dateApplied, AHStatus ahStatus) {
		AHApplication ahApplication = new AHApplication(dateApplied, ahStatus);
		entityManager.persist(ahApplication);
		return ahApplication;
	}

	public void createApplication(AHUser ahUser, AHJob ahJob, Date dateApplied, AHStatus applicationStatus){
		AHApplication ahApplication = new AHApplication(ahUser, ahJob, dateApplied, applicationStatus);
		entityManager.persist(ahApplication);

	}

	public AHApplication findApplication(Integer applicationId) {
		return entityManager.find(AHApplication.class, applicationId);
	}

	public AHApplication findApplication(AHJob job, AHUser user){

		Query query = entityManager.createQuery("SELECT a FROM AHApplication a WHERE a.ahJob = :job and a.ahUser =:user");
		query.setParameter("job", job);
		query.setParameter("user", user);

		AHApplication app = null;
		try{
			app = (AHApplication) query.getSingleResult();

		}catch(NoResultException e){
			return app;
		}
		return app;
	}

	public void updateApplication(AHApplication ahApplication) {
		AHApplication foundApplication = findApplication(ahApplication.getApplicationId());
		if(foundApplication != null){
			foundApplication = entityManager.merge(ahApplication);
		}

	}

	public void deleteApplication(Integer applicationId) {
		AHApplication foundApplication = findApplication(applicationId);
		if(foundApplication != null){
			entityManager.remove(foundApplication);
		}
	}

	public List<AHApplication> getAllApplicationsForPostedJobs(AHUser user,AHStatus status){
		TypedQuery<AHJob> query = 
				entityManager.createQuery("SELECT j from AHJob j WHERE j.ahUser =:user", 
						AHJob.class);
		query.setParameter("user", user);

		TypedQuery<AHApplication> appliedApp = 
				entityManager.createQuery("SELECT a from AHApplication a WHERE a.ahJob =:job and a.applicationStatus =:status", 
						AHApplication.class);

		List<AHJob> jobs = query.getResultList();
		List<AHApplication> allAppliedApplications = new ArrayList<AHApplication>();

		for(AHJob job: jobs){
			appliedApp.setParameter("job", job);
			appliedApp.setParameter("status", status);
			List<AHApplication> applications = appliedApp.getResultList();
			allAppliedApplications.addAll(applications);
		}

		return allAppliedApplications;

	}

	public List<AHApplication> getAllAppliedApplications(AHUser user, AHStatus status){

		TypedQuery<AHApplication> allAppliedApp = 
				entityManager.createQuery("SELECT a from AHApplication a WHERE a.ahUser =:user and a.applicationStatus =:status", 
						AHApplication.class);
		allAppliedApp.setParameter("user", user);
		allAppliedApp.setParameter("status", status);
		List<AHApplication> applications = allAppliedApp.getResultList();

		return applications;

	}

}
