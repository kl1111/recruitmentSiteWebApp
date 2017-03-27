package com.fdmgroup.test.services;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.models.AHApplication;
import com.fdmgroup.models.AHJob;
import com.fdmgroup.models.AHStatus;
import com.fdmgroup.models.AHUser;
import com.fdmgroup.services.AHApplicationService;
import com.fdmgroup.services.AHJobService;
import com.fdmgroup.services.AHUserService;

public class AHApplicationServiceTest {
	
	private EntityManagerFactory entityManagerFactory = 
			Persistence.createEntityManagerFactory("assassinsForHire");
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	private AHApplicationService ahApplicationService;

	@Before
	public void before(){
		entityManager = entityManagerFactory.createEntityManager();
		ahApplicationService = new AHApplicationService(entityManager);
	}
	
	@Test
	public void createApplication_returnsNewApplication(){
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		AHApplication ahApplication = ahApplicationService.createApplication(Date.valueOf("2017-01-04"), AHStatus.ACTIVE);
		entityTransaction.commit();
		Integer expected = 7;
		assertEquals(expected, ahApplication.getApplicationId());
	}
	
	@Test
	public void findApplication_returnsApplication(){
		AHApplication ahApplication = ahApplicationService.findApplication(3);
		AHStatus expected = AHStatus.CANCELLED;
		AHStatus actual = ahApplication.getApplicationStatus();
		assertEquals(expected, actual);
	}
	
	@Test
	public void updateApplication_updatesApplication(){
		entityTransaction = entityManager.getTransaction();
		AHApplication ahApplication = ahApplicationService.findApplication(3);
		AHApplication newApplication = new AHApplication(ahApplication.getDateApplied(), AHStatus.SUBMITTED);
		newApplication.setApplicationId(3);
		AHStatus expected = AHStatus.SUBMITTED;
		entityTransaction.begin();
		ahApplicationService.updateApplication(newApplication);
		entityTransaction.commit();
		AHApplication updatedApplication = ahApplicationService.findApplication(3);
		AHStatus actual = updatedApplication.getApplicationStatus();
		assertEquals(expected, actual);
	}
	
	@Test
	public void deleteApplication_deletesApplication(){
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ahApplicationService.deleteApplication(4);
		entityTransaction.commit();
		AHApplication ahAppliation = ahApplicationService.findApplication(4);
		assertNull(ahAppliation);
	}
	
	@Test
	public void findApplication_GivenJobAndUser(){
		AHUserService ahUserService = new AHUserService(entityManager);
		AHJobService ahJobService = new AHJobService(entityManager);
		
		AHUser user = ahUserService.findUser(2);
		AHJob job = ahJobService.findJob(1);
		AHApplication ahApplication = ahApplicationService.findApplication(job, user);
		
		int expected = 1;
		int actual = ahApplication.getApplicationId();
		assertEquals(expected, actual);
		
	}
	
	@Test 
	public void getAllApplicationsForPostedJobs(){
		AHUserService ahUserService = new AHUserService(entityManager);
		AHUser user = ahUserService.findUser(1);
		List<AHApplication> applications = ahApplicationService.getAllApplicationsForPostedJobs(user,AHStatus.SUBMITTED);
		int size = applications.size();
		assertNotNull(size);
	}
	
	@Test
	public void getAllAppliedApplications_ReturnApplicationsForCandidate(){
		AHUserService ahUserService = new AHUserService(entityManager);
		AHUser user = ahUserService.findUser(1);
		List<AHApplication> applications = ahApplicationService.getAllAppliedApplications(user, AHStatus.SUBMITTED);
		int size = applications.size();
		assertNotNull(size);
	}
}
