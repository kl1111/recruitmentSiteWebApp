package com.fdmgroup.validators;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fdmgroup.models.AHApplication;
import com.fdmgroup.models.AHJob;
import com.fdmgroup.models.AHUser;
import com.fdmgroup.services.AHApplicationService;
import com.fdmgroup.services.AHJobService;

public class AHApplicationValidator {

	private HttpSession session;


	public AHApplicationValidator(HttpServletRequest request) {
		session = request.getSession();
	}


	public boolean isUserLoggedIn(){
		if ((session.getAttribute("isLoggedIn") != null) &&
				(session.getAttribute("isLoggedIn").equals(true))){
			return true;
		}
		return false;
	}


	public boolean isNotApplied(AHUser user, AHJob ahJob, AHApplicationService ahApplicationService) {
		AHApplicationService applicationService = ahApplicationService;
		AHApplication application = applicationService.findApplication(ahJob, user);
		if(application !=null){
			return false;
		}

		return true;
	}







}