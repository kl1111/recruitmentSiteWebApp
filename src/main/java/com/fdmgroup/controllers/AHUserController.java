package com.fdmgroup.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.fdmgroup.models.AHStatus;
import com.fdmgroup.models.AHUser;
import com.fdmgroup.models.AHUserType;
import com.fdmgroup.services.AHUserService;
import com.fdmgroup.validators.AHUserValidator;

@Controller
public class AHUserController {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("assassinsForHire");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private AHUserService ahUserService;
	private AHUserValidator ahUserValidator;
	private HttpSession session;
	private EntityTransaction entityTransaction;

	@RequestMapping("/submitCandidateRegDetails")
	public String submitCandidateRegDetailsHandler(Model model, AHUser ahUser, HttpServletRequest request) {
		ahUserService = new AHUserService(entityManager);
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ahUserService.createUser(ahUser.getName(), ahUser.getEmail(), ahUser.getPassword(), AHUserType.CANDIDATE,
				AHStatus.ACTIVE);
		entityTransaction.commit();
		model.addAttribute(ahUser);
		return "candidateHomepage";

	}

	@RequestMapping("/submitRecruiterRegDetails")
	public String submitRecruiterRegDetailsHandler(Model model, AHUser ahUser, HttpServletRequest request) {
		ahUserService = new AHUserService(entityManager);
		ahUserService.createUser(ahUser.getName(), ahUser.getEmail(), ahUser.getPassword(), AHUserType.EMPLOYER,
				AHStatus.ACTIVE);
		return "recruiterHomepage";
	}

	@RequestMapping("/updateAccountDetails")
	public String updateAccountDetailHandler(Model model, AHUser ahUser, HttpServletRequest request) {
		ahUserService = new AHUserService(entityManager);
		AHUser currentUser = (AHUser) session.getAttribute("ahUser");
		ahUser.setUserId(currentUser.getUserId());
		ahUser.setRole(currentUser.getRole());
		ahUser.setUserStatus(currentUser.getUserStatus());
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ahUserService.updateUser(ahUser);
		entityTransaction.commit();
		model.addAttribute("ahUser", ahUser);
		if (ahUser.getRole() == AHUserType.CANDIDATE) {
		  return "redirect:/goToCandidateHomepage";
		}
		if (ahUser.getRole() == AHUserType.EMPLOYER) {
		  return "redirect:/goRecruiterToHomepage";
		}
		return null;
	}

	@RequestMapping("/deleteAccountDetails")
	public String deleteAccountDetails(HttpServletRequest request) {
		session = request.getSession();
		AHUser user = (AHUser) session.getAttribute("ahUser");

		ahUserService = new AHUserService(entityManager);
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ahUserService.deleteUser(user.getUserId());
		entityTransaction.commit();
		session.invalidate();
		return "AccountDeleteSuccessful";

	}

	@RequestMapping(value = "/submitLoginDetails", method = POST)

	public String submitLoginDetailsHandler(Model model, AHUser ahUser, HttpServletRequest request) {
		session = request.getSession();

		ahUserService = new AHUserService(entityManager);
		ahUserValidator = new AHUserValidator(ahUserService);
		AHUser user = ahUserService.findUserByEmail(ahUser.getEmail());
		if (ahUserValidator.isExistingUser(user) && ahUserValidator.isValidPassword(ahUser, user)) {
			if (user.getRole() == AHUserType.CANDIDATE) {
				if (user.getUserStatus() == AHStatus.ACTIVE) {
					session.setAttribute("isLoggedIn", true);
					session.setAttribute("ahUser", user);
					return "redirect:/goToCandidateHomepage";

				} else {
					return "login";
				}
			} else if (user.getRole() == AHUserType.EMPLOYER) {
				if (user.getUserStatus() == AHStatus.ACTIVE) {
					session.setAttribute("isLoggedIn", true);
					session.setAttribute("ahUser", user);
					return "redirect:/goRecruiterToHomepage";
				} else {
					return "login";
				}
			}
		}
		return "login";
	}
	
	@RequestMapping("/deactiveAccount")
	public String deactiveAcountHandler(HttpServletRequest request) {
		session = request.getSession();
		AHUser user = (AHUser) session.getAttribute("ahUser");
	
		ahUserService = new AHUserService(entityManager);
		
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ahUserService.deactiveUser(user.getUserId());
		entityTransaction.commit();
		session.invalidate();
		return "AccountDeactivatedSuccessful";

	}
	
	
	
	
	
}
