package com.fdmgroup.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.models.AHApplication;
import com.fdmgroup.models.AHJob;
import com.fdmgroup.models.AHStatus;
import com.fdmgroup.models.AHUser;
import com.fdmgroup.services.AHApplicationService;
import com.fdmgroup.services.AHJobService;
import com.fdmgroup.validators.AHApplicationValidator;

@Controller
public class AHApplicationController {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("assassinsForHire");;
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private EntityTransaction entityTransaction = entityManager.getTransaction();

	@RequestMapping(value = "/applyJob", method = POST)
	public String applyJobHandler(Model model, AHJob job, HttpServletRequest request) {

		HttpSession session = request.getSession();
		AHUser user = (AHUser) session.getAttribute("ahUser");
		AHApplicationValidator appValidator = new AHApplicationValidator(request);

		if (appValidator.isUserLoggedIn()) {

			AHApplicationService ahAppService = new AHApplicationService(entityManager);
			AHJobService ahJobService = new AHJobService(entityManager);
			AHJob ahJob = ahJobService.findJob(job.getJobId());

			if (appValidator.isNotApplied(user, ahJob, ahAppService)) {

				Calendar currenttime = Calendar.getInstance();
				Date applicationDate = new Date((currenttime.getTime()).getTime());
				
				entityTransaction.begin();
				ahAppService.createApplication(user, ahJob, applicationDate, AHStatus.SUBMITTED);
				entityTransaction.commit();
								
				return "ApplicationSuccessful";
			}else{  //if user has already applied
				
				return "ApplicationFailed"; 
			}
			
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/cancelApplication", method = POST)
	public String cancelApplicationHandler(Model model, AHApplication application, HttpServletRequest request) {
		AHApplicationService appService = new AHApplicationService(entityManager);
		AHApplication app = appService.findApplication(application.getApplicationId());
		app.setApplicationStatus(AHStatus.CANCELLED);
		
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		appService.updateApplication(app);
		entityTransaction.commit();
		
		return "redirect:/account";

	}
	
	@RequestMapping(value = "/resendApplication", method = POST)
	public String resendApplicationHandler(Model model, AHApplication application, HttpServletRequest request) {
		AHApplicationService appService = new AHApplicationService(entityManager);
		AHApplication app = appService.findApplication(application.getApplicationId());
		app.setApplicationStatus(AHStatus.SUBMITTED);
		
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		appService.updateApplication(app);
		entityTransaction.commit();
		
		return "redirect:/account";

	}
	
	@RequestMapping(value = "/deleteApplication", method = POST)
	public String deleteApplicationHandler(Model model, AHApplication application, HttpServletRequest request) {
		AHApplicationService appService = new AHApplicationService(entityManager);		
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		appService.deleteApplication(application.getApplicationId());
		entityTransaction.commit();
		
		return "redirect:/account";

	}
}
