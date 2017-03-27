package com.fdmgroup.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
import com.fdmgroup.models.AHUserType;
import com.fdmgroup.services.AHApplicationService;
import com.fdmgroup.services.AHJobService;
import com.fdmgroup.validators.AHApplicationValidator;

@Controller
public class MainController {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("assassinsForHire");;
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	@RequestMapping(value = "/")
	public String indexPageHandler(Model model, HttpServletRequest request) {
		AHJobService ahJobService = new AHJobService(entityManager);
		List<AHJob> allActiveJobs = ahJobService.getAllActiveJobs();
		model.addAttribute("allActiveJobs", allActiveJobs);

		return "index";
	}

	@RequestMapping("/index")
	public String returnToIPageHandler() {
		return "redirect:/";
	}

	@RequestMapping("/register")
	public String registerPageHandler() {
		return "register";
	}

	@RequestMapping("/login")
	public String loginPageHandler(Model model) {
		model.addAttribute("ahUser", new AHUser());
		return "login";
	}

	@RequestMapping("/logout")
	public String loginPageHandler(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/index";
	}

	@RequestMapping("/returnToAccountPage")
	public String returnToAccountPageHandler(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		AHUser ahUser = (AHUser) session.getAttribute("ahUser");
		model.addAttribute("ahUser", ahUser);
		return "account";
	}
	
	@RequestMapping("/account")
	public String accountPageHandler(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		AHApplicationValidator userStatus = new AHApplicationValidator(request);
		if (userStatus.isUserLoggedIn()) {
			AHUser ahUser = (AHUser) session.getAttribute("ahUser");
			if (ahUser.getRole() == AHUserType.CANDIDATE){
				return "redirect:/goToCandidateHomepage";
			}
			else if (ahUser.getRole() == AHUserType.EMPLOYER){
				return "redirect:/goRecruiterToHomepage";
			}
			else{
				return "redirect:/login";
			}
		}
		else{
			return "redirect:/login";
		}
	}
	
	@RequestMapping("/jobs")
	public String jobsHandler(Model model) {
		AHJobService ahJobService = new AHJobService(entityManager);
		List<AHJob> allActiveJobs = ahJobService.getAllActiveJobs();
		model.addAttribute("allActiveJobs", allActiveJobs);
		
		return "jobs";
	}

	@RequestMapping("/goToCandidateRegisterForm")
	public String registerCandidateFormHandler(Model model) {
		model.addAttribute("ahUser", new AHUser());
		return "candidateRegister";
	}

	@RequestMapping("/goToRecruiterRegisterForm")
	public String registerRecruiterFormHandler(Model model) {
		model.addAttribute("ahUser", new AHUser());
		return "recruiterRegister";
	}

	@RequestMapping("/goToCandidateHomepage")
	public String candidateHomepageHandler(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		AHUser user = (AHUser) session.getAttribute("ahUser");
		AHApplicationService ahApplicationService = new AHApplicationService(entityManager);
		List<AHApplication> allAppliedApplications = ahApplicationService.getAllAppliedApplications(user, AHStatus.SUBMITTED);
		List<AHApplication> allCancelledApplications = ahApplicationService.getAllAppliedApplications(user, AHStatus.CANCELLED);
		model.addAttribute("allAppliedApplications", allAppliedApplications);
		model.addAttribute("allCancelledApplications", allCancelledApplications);
		return "candidateHomepage";
	}

	@RequestMapping("/goRecruiterToHomepage")
	public String recruiterHomepageHandler(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		AHUser user = (AHUser) session.getAttribute("ahUser");
		AHApplicationService ahApplicationService = new AHApplicationService(entityManager);
		List<AHApplication> allAppliedApplications = ahApplicationService.getAllApplicationsForPostedJobs(user,AHStatus.SUBMITTED);
		model.addAttribute("allAppliedApplications", allAppliedApplications);
		return "recruiterHomepage";
	}

	@RequestMapping("/goToPostJob")
	public String postJobpageHandler(Model model) {
		model.addAttribute("ahJob", new AHJob());
		return "postJob";
	}
	


	@RequestMapping("/candidateApplications")
	public String candidateApplicationsPageHandler() {
		return "candidateApplications";
	}
}
