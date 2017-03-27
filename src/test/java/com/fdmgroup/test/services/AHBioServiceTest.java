package com.fdmgroup.test.services;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.models.AHBio;
import com.fdmgroup.models.AHUser;
import com.fdmgroup.services.AHBioService;

public class AHBioServiceTest {
	
	private EntityManagerFactory entityManagerFactory = 
			Persistence.createEntityManagerFactory("assassinsForHire");
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	private AHBioService ahBioService;
	
	@Before
	public void before(){
		entityManager = entityManagerFactory.createEntityManager();
		ahBioService = new AHBioService(entityManager);
	}
	
	@Test
	public void createBio_returnsNewBio(){
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		AHBio ahBio = ahBioService.createBio(new AHUser(), "I am amazing", "killing"); 
		entityTransaction.commit();
		Integer expected = 7;
		assertEquals(expected, ahBio.getBioId());
	}
	
	@Test
	public void findBio_returnsBio(){
		AHBio ahBio = ahBioService.findBio(6);
		String expected = "this is my bio";
		String actual = ahBio.getBio();
		assertEquals(expected, actual);
	}
	
	@Test
    public void findBioBySkill_ShouldReturnListOfBios(){
           List<AHBio> ahBio = ahBioService.findBioBySkill("Kidnapping");
           assertTrue(ahBio.size() > 1);
    }

	
	@Test
	public void updateBio_shouldUpdateBio(){
		entityTransaction = entityManager.getTransaction();
		AHBio ahBio = ahBioService.findBio(2);
		AHBio newBio = new AHBio(ahBio.getUser(), "Fantastic at throat slitting", ahBio.getSkills());
		newBio.setBioId(2);
		String expected = "Fantastic at throat slitting";
		entityTransaction.begin();
		ahBioService.updateBio(newBio);
		entityTransaction.commit();
		AHBio updatedBio = ahBioService.findBio(2);
		String actual = updatedBio.getBio();
		assertEquals(expected, actual);
	}
	
	@Test
	public void deleteBio_ShouldDeleteBio(){
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ahBioService.deleteBio(3);
		entityTransaction.commit();
		AHBio ahBio = ahBioService.findBio(3);
		assertNull(ahBio);
	}

}
