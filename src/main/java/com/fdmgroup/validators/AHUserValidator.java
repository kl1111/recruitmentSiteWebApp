package com.fdmgroup.validators;

import com.fdmgroup.models.AHStatus;
import com.fdmgroup.models.AHUser;
import com.fdmgroup.services.AHUserService;

public class AHUserValidator {

	private AHUserService ahUserService;

	public AHUserValidator(AHUserService ahUserService) {
		this.ahUserService = ahUserService;
	}

	public boolean isExistingUser(AHUser user) {
		return user != null;
	}

	public boolean isValidPassword(AHUser ahUserSupplied, AHUser ahUserInDB) {
		return ahUserSupplied.getPassword().equals(ahUserInDB.getPassword());
	}

	public boolean isActiveUser(AHUser ahUserSupplied) {
		return ahUserSupplied.getUserStatus() == AHStatus.ACTIVE;
	}
}
