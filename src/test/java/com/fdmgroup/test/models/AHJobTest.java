package com.fdmgroup.test.models;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.models.AHApplication;
import com.fdmgroup.models.AHJob;
import com.fdmgroup.models.AHStatus;
import com.fdmgroup.models.AHUser;

public class AHJobTest {

	private AHJob ahJob;
	private AHUser ahUser;
	
	@Before
	public void before() {
		ahUser = new AHUser();
		ahJob = new AHJob(Date.valueOf("2016-12-02"),
				"Java Developer",
				"Fantastic opportunity for Java Developers in Sydney", 
				AHStatus.ACTIVE);
	}

	@Test
	public void getJobId_shouldReturnJobId() {
		ahJob.setJobId(1);
		int expected = 1;
		int actual = ahJob.getJobId();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getJobTitle_shouldReturnJobTitle() {
		String expected = "Java Developer";
		String actual = ahJob.getJobTitle();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getUser_shouldReturnAHUser() {
		AHUser expected = ahUser;
		ahJob.setUser(ahUser);
		AHUser actual = ahJob.getUser();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getDatePosted_shouldReturnDatePosted() {
		Date expected = Date.valueOf("2016-12-02");
		Date actual = ahJob.getDatePosted();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getDescription_shouldReturnDescription() {
		String expected = "Fantastic opportunity for Java Developers in Sydney";
		String actual = ahJob.getDescription();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getJobStatus_shouldReturnJobStatus() {
		AHStatus expected = AHStatus.ACTIVE;
		AHStatus actual = ahJob.getJobStatus();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getApplications_returnsListOfApplications(){
		AHApplication ahApplication = new AHApplication();
		ahJob.addApplication(ahApplication);
		List<AHApplication> applications = ahJob.getApplications();
		assertNotNull(applications);
	}
}
