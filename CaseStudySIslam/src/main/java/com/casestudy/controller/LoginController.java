package com.casestudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.casestudy.repository.CredentialRepository;
import com.casestudy.service.CredentialService;

@Controller
@Transactional
public class LoginController {

	@Autowired
	CredentialRepository credentialRepository;

	@Autowired
	CredentialService credentialService;

	@RequestMapping("/login")
	public ModelAndView initAdmin(Model model) {
		SecurityContextHolder.getContext().setAuthentication(null);
		ModelAndView mav = new ModelAndView("login");
		if (model.getAttribute("message") != null)
			mav.addObject("message", model.getAttribute("message"));
		return mav;
	}
}
