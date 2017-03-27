package com.fdmgroup.test.models;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.models.AHApplication;
import com.fdmgroup.models.AHJob;
import com.fdmgroup.models.AHStatus;
import com.fdmgroup.models.AHUser;

public class AHApplicationTest {

	private AHApplication ahApplication;
	private AHUser ahUser;
	private AHJob ahJob;
	
	@Before
	public void before() {
		ahUser = new AHUser();
		ahJob = new AHJob();
		ahApplication = new AHApplication(Date.valueOf("2016-12-02"), 
				AHStatus.SUBMITTED);
	}
	
	@Test
	public void getApplicationID_returnsAppID(){
		ahApplication.setApplicationId(1);
		int expected = 1;
		int actual = ahApplication.getApplicationId();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getUser_returnsUser(){
		ahApplication.setUser(ahUser);
		AHUser expected = ahUser;
		AHUser actual = ahApplication.getUser();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getJob_returnsJob(){
		ahApplication.setJob(ahJob);
		AHJob expected = ahJob;
		AHJob actual = ahApplication.getJob();
		assertEquals(expected, actual);	
	}
	
	@Test
	public void getDateApplied_returnsDateApplied(){
		Date expected = Date.valueOf("2016-12-02");
		Date actual = ahApplication.getDateApplied();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getApplicationStatus_returnsApplicationStatus(){
		AHStatus expected = AHStatus.SUBMITTED;
		AHStatus actual = ahApplication.getApplicationStatus();
		assertEquals(expected, actual);
	}
}
