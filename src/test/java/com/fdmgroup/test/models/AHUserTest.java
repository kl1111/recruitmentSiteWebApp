package com.fdmgroup.test.models;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.models.AHApplication;
import com.fdmgroup.models.AHBio;
import com.fdmgroup.models.AHJob;
import com.fdmgroup.models.AHStatus;
import com.fdmgroup.models.AHUser;
import com.fdmgroup.models.AHUserType;

public class AHUserTest {

	private AHUser ahUser;
	
	@Before
	public void before(){
		ahUser = new AHUser("JAMES", "james@mail.com", "password", AHUserType.CANDIDATE, AHStatus.ACTIVE);
	}
	
	@Test
	public void test_getName_ReturnUserFullname(){
		String expected = "JAMES";
		String actual = ahUser.getName();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_setName_UsernameIsChangedToKelly(){
		String expected = "KELLY";
		ahUser.setName("KELLY");
		String actual = ahUser.getName();
		assertEquals(expected, actual);
	}

	@Test
	public void test_getEmail_ReturnEmailAddress(){
		String expected = "james@mail.com";
		String actual = ahUser.getEmail();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_setEmail_emailIsChanged(){
		String expected = "j@mail.com";
		ahUser.setEmail("j@mail.com");
		String actual = ahUser.getEmail();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_getPassword_ReturnPassword(){
		String expected = "password";
		String actual = ahUser.getPassword();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_setPassword_passwordIsChanged(){
		
		ahUser.setPassword("noPass");
		String expected = "noPass";
		String actual = ahUser.getPassword();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void test_getRole_returnRole(){
		AHUserType expected = AHUserType.CANDIDATE;
		AHUserType actual = ahUser.getRole();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void test_setRole(){
		AHUserType expected = AHUserType.EMPLOYER;
		ahUser.setRole(AHUserType.EMPLOYER);
		
		AHUserType actual = ahUser.getRole();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void test_getBio_returnsBio(){
		AHBio ahBio = new AHBio();
		ahUser.setBio(ahBio);
		assertNotNull(ahUser.getBio());	
	}
	
	@Test
	public void test_getUserStatus_returnsStatus(){
		AHStatus expected = AHStatus.ACTIVE;
		AHStatus actual = ahUser.getUserStatus();
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_getApplications_shouldReturnListOfApplications() {
		List<AHApplication> applications = ahUser.getApplications();
		assertNotNull(applications);
	}
	
	@Test
	public void test_addApplication_shouldAddApplicationToApplications() {
		ahUser.addApplication(new AHApplication());
		List<AHApplication> applications = ahUser.getApplications();
		assertTrue(applications.size() == 1);
	}
	
	@Test
	public void test_getJobs_shouldReturnListOfJobs() {
		List<AHJob> jobs = ahUser.getJobs();
		assertNotNull(jobs);
	}
	
	@Test
	public void test_addJob_shouldAddJobToJobs() {
		ahUser.addJob(new AHJob());
		List<AHJob> jobs = ahUser.getJobs();
		assertTrue(jobs.size() == 1);
	}
}
