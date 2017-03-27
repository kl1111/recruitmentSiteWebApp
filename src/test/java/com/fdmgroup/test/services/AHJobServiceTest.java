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

import com.fdmgroup.models.AHJob;
import com.fdmgroup.models.AHStatus;
import com.fdmgroup.models.AHUser;
import com.fdmgroup.services.AHJobService;
import com.fdmgroup.services.AHUserService;

public class AHJobServiceTest {

	private EntityManagerFactory entityManagerFactory = 
			Persistence.createEntityManagerFactory("assassinsForHire");
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	private AHJobService ahJobService;

	@Before
	public void before(){
		entityManager = entityManagerFactory.createEntityManager();
		ahJobService = new AHJobService(entityManager);
	}

	@Test
	public void createJob_returnsNewJob(){
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		AHJob ahJob = ahJobService.createJob(Date.valueOf("2017-01-04"), 
				"Ace Killer", 
				"Manual labour, requires removal of dead bodies", 
				AHStatus.ACTIVE);
		entityTransaction.commit();
		Integer expected = 7;
		assertEquals(expected, ahJob.getJobId());
	}
	
	@Test
	public void createJob_With5ArguementsInConstractor_returnsNewJob(){
		AHUserService ahUserService = new AHUserService(entityManager);
		AHUser ahUser = ahUserService.findUser(1);
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		AHJob ahJob = ahJobService.createJob(ahUser,
				Date.valueOf("2017-01-04"), 
				"Ace Killer", 
				"Manual labour, requires removal of dead bodies", 
				AHStatus.ACTIVE);
		entityTransaction.commit();
		Integer expected = 8;
		assertEquals(expected, ahJob.getJobId());
	}

	@Test
	public void findJob_returnsJob(){
		AHJob ahJob = ahJobService.findJob(2);
		String expected = "this description of the job advertised";
		String actual = ahJob.getDescription();
		assertEquals(expected, actual);
	}

	@Test
	public void findJobByJobTitle_shouldReturnListOfJobs() {
		List<AHJob> ahJobs = ahJobService.findJobsByJobTitle("Here");
		assertTrue(ahJobs.size() > 3);
	}

	@Test
	public void updateJob_shouldUpdateJob(){
		entityTransaction = entityManager.getTransaction();
		AHJob ahJob = ahJobService.findJob(3);
		AHJob newJob = new AHJob(ahJob.getDatePosted(), 
				"Ace Killer", 
				"Manual labour, requires removal of dead bodies", 
				ahJob.getJobStatus());
		newJob.setJobId(3);
		String expected = "Manual labour, requires removal of dead bodies";
		entityTransaction.begin();
		ahJobService.updateJob(newJob);
		entityTransaction.commit();
		AHJob updatedJob = ahJobService.findJob(3);
		String actual = updatedJob.getDescription();
		assertEquals(expected, actual);	
	}

	@Test
	public void deleteJob_deletedJob(){
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ahJobService.deleteJob(4);
		entityTransaction.commit();
		AHJob ahJob = ahJobService.findJob(4);
		assertNull(ahJob);
	}

	@Test
	public void getAllActiveJobs_returnAllJobsActive(){


		List<AHJob> allActiveJobs = ahJobService.getAllActiveJobs();
		int actual = allActiveJobs.size();

		assertTrue(actual > 3);
	}

}
