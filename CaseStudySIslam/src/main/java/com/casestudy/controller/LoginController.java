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
	public void initAdmin() {

	}
}
