package com.fdmgroup.test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import com.fdmgroup.models.AHUserType;
import com.fdmgroup.services.AHUserService;

public class AHUserServiceTest {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("assassinsForHire");
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	private AHUserService ahUserService;

	@Before
	public void before() {
		entityManager = entityManagerFactory.createEntityManager();
		ahUserService = new AHUserService(entityManager);
	}

	@Test
	public void createUser_shouldreturnNewUser() {
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		AHUser ahUser = ahUserService.createUser("JAMES", "james@mail.com", "password", AHUserType.CANDIDATE,
				AHStatus.ACTIVE);
		entityTransaction.commit();
		int expected = 7;
		assertEquals(expected, ahUser.getUserId());

	}

	@Test
	public void findUser_shouldReturnUser() {
		AHUser ahUser = ahUserService.findUser(2);
		String expected = "Jack@hotmail.co.uk";
		String actual = ahUser.getEmail();
		assertEquals(expected, actual);
	}

	@Test
	public void updateUser_shouldUpdateUser() {
		entityTransaction = entityManager.getTransaction();
		AHUser ahUser = ahUserService.findUser(4);
		AHUser newUser = new AHUser(ahUser.getName(), ahUser.getEmail(), "qwerty", ahUser.getRole(),
				ahUser.getUserStatus());
		newUser.setUserId(4);
		String expected = "qwerty";
		entityTransaction.begin();
		ahUserService.updateUser(newUser);
		entityTransaction.commit();
		AHUser updatedUser = ahUserService.findUser(4);
		String actual = updatedUser.getPassword();
		assertEquals(expected, actual);
	}

	@Test
	public void deleteUser_shouldDeleteUser() {
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ahUserService.deleteUser(5);
		entityTransaction.commit();
		AHUser ahUser = ahUserService.findUser(5);
		assertNull(ahUser);
	}

	@Test
	public void findUser_shouldFindUserByName() {
		AHUser user = ahUserService.findUser("Jack");
		String expected = "Jack";
		String actual = user.getName();
		assertEquals(expected, actual);
	}

	@Test
	public void findUserByEmail_shouldFindUserByEmail() {
		AHUser ahUser = ahUserService.findUserByEmail("Oliver@hotmail.co.uk");
		int expected = 1;
		int actual = ahUser.getUserId();
		assertEquals(expected, actual);
	}

	@Test
	public void deactiveUser_shouldSetUserStatusToInactive() {
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ahUserService.deactiveUser(1);
		entityTransaction.commit();
		AHUser ahUser = ahUserService.findUser(1);
		AHStatus expected = AHStatus.INACTIVE;
		AHStatus actual = ahUser.getUserStatus();
		assertEquals(expected, actual);

	}

	@Test
	public void isNameTaken_shouldReturnTrue() {
		boolean actual = ahUserService.isNameTaken("Jack");
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	public void isNameTaken_ShouldReturnFalse() {
		boolean actual = ahUserService.isNameTaken("testUsernameFalse");
		boolean expected = false;
		assertEquals(expected, actual);
	}

}
