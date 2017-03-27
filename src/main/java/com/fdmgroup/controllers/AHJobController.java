package com.fdmgroup.controllers;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.models.AHJob;
import com.fdmgroup.models.AHStatus;
import com.fdmgroup.models.AHUser;
import com.fdmgroup.services.AHJobService;

@Controller
public class AHJobController {

	private EntityManagerFactory entityManagerFactory = 
			Persistence.createEntityManagerFactory("assassinsForHire");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private EntityTransaction entityTransaction = entityManager.getTransaction();
	private AHJobService ahJobService = new AHJobService(entityManager);


	@RequestMapping(value = "/jobsByJobTitle", method = POST)
	public String findJobsByJobTitleHandler(Model model, HttpServletRequest request, @RequestParam("jobTitle") String jobTitle) {
		List<AHJob> jobsByJobTitle = ahJobService.findJobsByJobTitle(jobTitle);
		model.addAttribute("jobTitle", jobTitle);
		model.addAttribute("jobsByJobTitle", jobsByJobTitle);
		return "jobsByJobTitle";

	}

	@RequestMapping(value = "/postANewJob")
	public String postANewJobHandler(HttpServletRequest request, @RequestParam("jobTitle") String jobTitle, @RequestParam("description") String description){
		HttpSession session = request.getSession();
		AHUser user = (AHUser) session.getAttribute("ahUser");
		
		AHJobService ahJobService = new AHJobService(entityManager);
		Calendar currenttime = Calendar.getInstance();
		Date jobPostedDate = new Date((currenttime.getTime()).getTime());
		
		entityTransaction.begin();
		ahJobService.createJob(user,jobPostedDate, jobTitle, description, AHStatus.ACTIVE);
		entityTransaction.commit();
		return "postJobSuccessful";

	}
}
