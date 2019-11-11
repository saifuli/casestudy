package com.casestudy.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casestudy.config.BCrpytPasswordEncoderEdited;
import com.casestudy.dao.PictureDAO;
import com.casestudy.dao.PostDAO;
import com.casestudy.dao.UserDAO;
import com.casestudy.model.Credential;
import com.casestudy.model.Picture;
import com.casestudy.model.Post;
import com.casestudy.model.User;
import com.casestudy.repository.CredentialRepository;
import com.casestudy.repository.UserRepository;

@PropertySource("classpath:app.properties")
@Controller
public class HomeController {

	@Autowired
	CredentialRepository credentialRepository;

	@RequestMapping("/")
	public ModelAndView getLanding() {
		ModelAndView mav = new ModelAndView("landing");
		return mav;
	}

	@RequestMapping("/home")
	public ModelAndView getHome(Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("home");
		if (principal.getName() == null)
			return new ModelAndView("redirect:/login");

		Credential credential = credentialRepository.findCredentialByEmail(principal.getName());
		mav.addObject("credential", credential);
		return mav;
	}

	@RequestMapping("/contactus")
	public ModelAndView getContact() {
		System.out.println("In contact!");
		ModelAndView mav = new ModelAndView("contact");
		return mav;
	}

	@RequestMapping("/403")
	public ModelAndView get403Page(Principal principal) {
		ModelAndView mav = new ModelAndView("403page");
		if (principal != null)
			mav.addObject("message", "Hi " + principal.getName() + " you don't have access to this page!");
		else {
			mav.addObject("message", "You don't have to this page!");
		}
		return mav;
	}
}
