package com.fdmgroup.test.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.models.AHBio;
import com.fdmgroup.models.AHUser;

public class AHBioTest {

	private AHBio ahBio;
	private AHUser ahUser;
	
	@Before
	public void before() {
		ahUser = new AHUser();
		ahBio = new AHBio(ahUser, 
				"A specialist in extortion, kidnappig and many others...", 
				"Extortion, Kidnapping");
	}
	
	@Test
	public void getBioId_shouldReturnBioId() {
		ahBio.setBioId(1);
		Integer expected = 1;
		Integer actual = ahBio.getBioId();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getUser_shouldReturnUser() {
		AHUser expected = ahUser;
		AHUser actual = ahBio.getUser();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getBio_shouldReturnBio() {
		String expected = "A specialist in extortion, kidnappig and many others...";
		String actual = ahBio.getBio();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getSkills_shouldReturnSkills() {
		String expected = "Extortion, Kidnapping";
		String actual = ahBio.getSkills();
		assertEquals(expected, actual);
	}
}
