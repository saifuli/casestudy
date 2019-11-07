package com.casestudy.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.casestudy.model.Authorities;
import com.casestudy.model.Credential;
import com.casestudy.model.User;
import com.casestudy.repository.CredentialRepository;
import com.casestudy.repository.LoginRepository;
import com.casestudy.service.CredentialService;
import com.casestudy.service.LoginService;

@Controller
@Transactional
public class LoginController {
	
	@Autowired
	CredentialRepository credentialRepository;
	
	@Autowired
	CredentialService credentialService;
	
	@RequestMapping("/login")
	public void initAdmin(Credential cred) {

		Credential credential = credentialRepository.findCredentialByEmail(cred.getEmail());

		
	}

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public ModelAndView getLoginForm() {
//		System.out.println("in login");
//		ModelAndView mav = new ModelAndView("login");
//		mav.addObject("loginForm", new Credential());
//		return mav;
//	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(@Valid @ModelAttribute("loginForm") Credential cred, BindingResult br) {
		ModelAndView mav = null;
		Credential credential = credentialRepository.findCredentialByEmail(cred.getEmail());
		System.out.println("in loginprocess");
		
		System.out.println("login name: " + cred.getUsername());
		System.out.println(br.toString());

		if (!br.hasErrors() || (br.getFieldValue("email") != null
				&& br.getFieldValue("password") != null)) {
			System.out.println("no errors");
			if (cred != null && credential.getEmail().equals(cred.getEmail())
					&& credential.getPassword().equals(cred.getPassword())) {
				System.out.println("validation passed");
				mav = new ModelAndView("welcome");
				mav.addObject("credential", credential);
			} else {
				System.out.println("validation did not pass");
				mav = new ModelAndView("loginForm");
				mav.addObject("message", "Username or Password is wrong!");
			}
			return mav;
		} else {
			System.out.println("errors occurred");
			System.out.println(br.toString());
			mav = new ModelAndView("loginForm");

			return mav;
		}

	}
}
