package com.fdmgroup.test.validators;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.models.AHStatus;
import com.fdmgroup.models.AHUser;
import com.fdmgroup.models.AHUserType;
import com.fdmgroup.services.AHUserService;
import com.fdmgroup.validators.AHUserValidator;

public class AHUserValidatorTest {

	private EntityManagerFactory entityManagerFactory =
			Persistence.createEntityManagerFactory("assassinsForHire");
	private EntityManager entityManager;
	private AHUserService ahUserService;
	private AHUserValidator ahUserValidator;
	
	@Before
	public void before() {
		entityManager = entityManagerFactory.createEntityManager();
		ahUserService = new AHUserService(entityManager);
		ahUserValidator = new AHUserValidator(ahUserService);
	}
	
	@Test
	public void isExistingUser_shouldCheckIfUserFound() {
		AHUser ahUser = ahUserService.findUser(5);
		boolean isExistingUser = ahUserValidator.isExistingUser(ahUser);
		assertTrue(isExistingUser);
	}
	
	@Test
	public void isValidPassword_shouldConfirmThatPasswordMatches() {
		AHUser ahUser1 = ahUserService.findUser(5);
		AHUser ahUser2 = ahUserService.findUser(5);
		boolean isValidPassword = ahUserValidator.isValidPassword(ahUser1, ahUser2);
		assertTrue(isValidPassword);
	}
	
	@Test
	public void isValidPassword_shouldConfirmThatPasswordDoesNotMatch() {
		AHUser ahUser1 = ahUserService.findUser(5);
		AHUser ahUser2 = new AHUser("Steve", 
				"Charlie@hotmail.co.uk", 
				"otherPassword",
				AHUserType.CANDIDATE, 
				AHStatus.ACTIVE);
		boolean isValidPassword = ahUserValidator.isValidPassword(ahUser1, ahUser2);
		assertFalse(isValidPassword);
	}
	
	@Test
	public void isActiveUserr_shouldConfirmThatUserIsActive() {
		AHUser ahUser = ahUserService.findUser(5);
		boolean isActiveUser = ahUserValidator.isActiveUser(ahUser);
		assertTrue(isActiveUser);
	}
	
	@Test
	public void isActiveUser_shouldConfirmThatUserIsInactive() {
		AHUser ahUser = new AHUser("Steve", 
				"Charlie@hotmail.co.uk", 
				"otherPassword",
				AHUserType.CANDIDATE, 
				AHStatus.INACTIVE);
		boolean isActiveUser = ahUserValidator.isActiveUser(ahUser);
		assertFalse(isActiveUser);
	}
}
