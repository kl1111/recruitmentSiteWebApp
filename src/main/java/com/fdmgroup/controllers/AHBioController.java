package com.fdmgroup.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.models.AHBio;
import com.fdmgroup.models.AHUser;

@Controller
public class AHBioController {
	
	//private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("assassinsForHire");
	//private EntityManager ahBioEntityManager = entityManagerFactory.createEntityManager();
	//private AHBioService ahBioService = new AHBioService(ahBioEntityManager);
	
	@RequestMapping("/updateBio")
	public String updateBioHandler(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		AHUser ahUser = (AHUser) session.getAttribute("ahUser");
		System.out.println("ahUser get bio " + ahUser.getEmail());
		AHBio ahBio = ahUser.getBio();
		if (ahBio == null) {
			// the user is not added correctly
			ahBio = new AHBio(ahUser, null, null);
			session.setAttribute("ahBio", ahBio);
			model.addAttribute("ahBio", ahBio);
			System.out.println("Create new bio! " + ahBio.getUser().getEmail());
			return "bio";
		}
		System.out.println("and some bio " + ahBio.getBio());
		session.setAttribute("ahBio", ahBio);
		model.addAttribute("ahBio", ahBio);
		return "bio";
	}
	
	@RequestMapping("/updateBioDetails")
	public String updateBioDetailsHandler(Model model, @RequestParam("bio") String bio, @RequestParam("skill") String skill, HttpServletRequest request) {
		HttpSession session = request.getSession();
		// need to save bio
		
		System.out.println("bio: " + bio);
		System.out.println("skill: " + skill);
		
		System.out.println("Bio updated!");
		AHBio currentBio = (AHBio) session.getAttribute("ahBio");
		System.out.println(currentBio.getBio());
		return "candidateHomepage";
	}
	
}
