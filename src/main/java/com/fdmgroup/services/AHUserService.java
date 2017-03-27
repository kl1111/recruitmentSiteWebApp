package com.fdmgroup.services;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.fdmgroup.models.AHStatus;
import com.fdmgroup.models.AHUser;
import com.fdmgroup.models.AHUserType;

public class AHUserService {

	private EntityManager entityManager;

	public AHUserService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public AHUser createUser(String name,
			String email,
			String password, 
			AHUserType role,
			AHStatus userStatus) {
		AHUser ahUser = new AHUser(name, email, password, role, userStatus);
		entityManager.persist(ahUser);
		return ahUser;
	}

	public AHUser findUser(int userId) {
		return entityManager.find(AHUser.class, userId);
	}

	public AHUser findUser(String name) {
		Query query = entityManager.createNamedQuery("find user by name");
		query.setParameter("name", name);
		AHUser result = null;
		try {
			result = (AHUser) query.getSingleResult();
		} catch (NoResultException e) {
			return result;
		}
		return result;
	}
	
	public AHUser findUserByEmail(String email) {
		Query query = entityManager.createQuery("SELECT u FROM AHUser u WHERE u.email = :email");
		query.setParameter("email", email);
		AHUser result = null;
		try {
			result = (AHUser) query.getSingleResult();
		} catch (NoResultException e) {
			return result;
		}
		return result;
	}

	public void updateUser(AHUser ahUser) {
		AHUser foundUser = findUser(ahUser.getUserId());
		if (foundUser != null) {
			foundUser = entityManager.merge(ahUser);
		}
	}

	public void deleteUser(int userId) {
		AHUser foundUser = findUser(userId);
		if (foundUser != null) {
			entityManager.remove(foundUser);
		}
	}

	public void deactiveUser(int userId) {
		AHUser ahUser = findUser(userId);
		AHUser newUser = new AHUser(ahUser.getName(), 
				ahUser.getEmail(), 
				ahUser.getPassword(), 
				ahUser.getRole(),
				AHStatus.INACTIVE);
		newUser.setUserId(ahUser.getUserId());
		if (ahUser != null) {
			ahUser = entityManager.merge(newUser);
		}
	}

	public boolean isNameTaken(String name) {

		if (findUser(name) != null) {
			return true;
		} else {
			return false;
		}
	}

}
