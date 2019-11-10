package com.casestudy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.casestudy.dao.UserDAO;
import com.casestudy.model.Authorities;
import com.casestudy.model.Credential;
import com.casestudy.model.User;
import com.casestudy.repository.CredentialRepository;
import com.casestudy.service.CredentialService;


@Transactional
@Controller
public class UserRegistrationController {

	@Autowired
	CredentialService credentialService;
	
	@Autowired
	CredentialRepository credentialRepository;

	
	
	@Autowired
	UserDAO userDAO;
	
//	@Autowired
//	UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(sdf, false));
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView getUserCredentialForm() {
		ModelAndView mav = new ModelAndView("userCredentialForm");
		mav.addObject("userCredentialFormObj", new Credential());
		mav.addObject("userFormObj", new User());
		mav.addObject("action", "register");
		return mav;
	}

	
	
	@RequestMapping(value = "/processUserCredential", method = RequestMethod.POST)
	public ModelAndView loginProcess(@Valid @ModelAttribute("userCredentialFormObj") Credential cred,
			@ModelAttribute("userFormObj") User user, BindingResult br,
			@RequestParam("confPassword") String confPassword) {
		Credential credential = credentialRepository.findCredentialByEmail(cred.getEmail());
		ModelAndView mav = null;
		System.out.println("outside");
		if (!br.hasErrors() || (br.getFieldValue("user") == null)) {
			System.out.println("inside no errors");
			if (cred.getPassword().equals(confPassword)) {
				System.out.println("password is the same");
				if (credential == null) {
					
					System.out.println(cred.toString());
					System.out.println(user.toString());
					String hashPass = new BCryptPasswordEncoder().encode(cred.getPassword());
					Authorities authorities = new Authorities();
					authorities.setAuthority("ROLE_ADMIN");
					Set<Authorities> authorities2 = new HashSet<Authorities>();
					authorities2.add(authorities);
					
					credential = new Credential(cred.getEmail(), cred.getUsername(), hashPass, authorities2, new Date());

					authorities2.remove(authorities);
					authorities.setCredential(credential);
					authorities2.add(authorities);
					
					credential.setAuthorities(authorities2);
					User u = new User(user.getName());
					u.setCredential(credential);
					credential.setUser(u);
					

					credentialService.saveCredential(credential);
					
					
					System.out.println("registering");
					mav = new ModelAndView("registerConfirmation");
					mav.addObject("credential", credentialRepository.findCredentialByEmail(credential.getEmail()));
					mav.addObject("message", "Credential successfully created!");
					return mav;
				} else {
					if (!confPassword.equals("")) {
						System.out.println("pass equals");
						System.out.println("updating");
						User u = credential.getUser();
						u.setName(user.getName());
						credential.setUser(u);
						credential.setPassword(cred.getPassword());
						System.out.println(credential.toString());
					}
					credentialService.saveCredential(credential);
					mav = new ModelAndView("redirect:/user?u=" + credential.getEmail());
				}
			} else {
				System.out.println("password is not the same");
				mav = new ModelAndView("userCredentialForm");
				mav.addObject("message", "Password did not match!");
			}
		} else {
			System.out.println("has errors");
			System.out.println(br.toString());
			mav = new ModelAndView("redirect:/register");
		}
		return mav;
	}
}
