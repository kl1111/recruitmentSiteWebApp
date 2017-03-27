package com.fdmgroup.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.models.AHBio;
import com.fdmgroup.models.AHUser;

public class AHBioService {
	
	private EntityManager entityManager;

	public AHBioService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public AHBio createBio(AHUser ahUser, String bio, String skills) {
		AHBio ahBio = new AHBio(ahUser, bio, skills);
		entityManager.persist(ahBio);
		return ahBio;
	}

	public AHBio findBio(int ahBioId) {
		return entityManager.find(AHBio.class, ahBioId);
	}
	
	public List<AHBio> findBioBySkill(String skills){
        TypedQuery<AHBio> query = 
        		entityManager.createQuery("SELECT b from AHBio b WHERE b.skills LIKE CONCAT('%',:skills,'%')", 
        		AHBio.class);
        query.setParameter("skills", skills);
        return query.getResultList();
 }      


	public void updateBio(AHBio ahBio) {
		AHBio foundBio = findBio(ahBio.getBioId());
		if(foundBio != null) {
			foundBio = entityManager.merge(ahBio);
		}
	}

	public void deleteBio(int ahBioId) {
		AHBio foundBio = findBio(ahBioId);
		if (foundBio != null) {
			entityManager.remove(foundBio);
		}
	}

}
