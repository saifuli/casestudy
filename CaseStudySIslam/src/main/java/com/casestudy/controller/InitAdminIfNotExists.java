package com.casestudy.controller;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.casestudy.model.Authorities;
import com.casestudy.model.Credential;
import com.casestudy.model.User;
import com.casestudy.repository.CredentialRepository;
import com.casestudy.service.CredentialService;

@Controller
@Transactional
public class InitAdminIfNotExists {

	@Autowired
	CredentialService credentialService;

	@Autowired
	CredentialRepository credentialRepository;

	//Optional
	@RequestMapping("/login1")
	public void initAdmin() {

		Credential credential = credentialRepository.findByEmail("a@gmail.com");

		if (credential == null) {
			System.out.println("Creating admin...");
			User adminUser = new User();
			credential.setUsername("Kun");
			adminUser.setName("kun");
			credential.setJoinDate(new Date());
			String encoded = new BCryptPasswordEncoder().encode("123456");
			credential.setPassword(encoded);
			credential.setUser(adminUser);

			Authorities role = new Authorities();
			role.setCredential(credential);
			role.setAuthority("ROLE_ADMIN");
			
			credential.getAuthorities().add(role);
			
			credentialService.saveUser(credential);
		}
	}

}
