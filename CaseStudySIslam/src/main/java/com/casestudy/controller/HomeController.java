package com.casestudy.controller;

import java.io.File;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.casestudy.model.Credential;
import com.casestudy.model.Picture;
import com.casestudy.model.Post;
import com.casestudy.repository.CredentialRepository;

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
		System.out.println(principal.getName());
		Credential credential = credentialRepository.findByEmail(principal.getName());
		mav.addObject("credential", credential);
		return mav;
	}

	@RequestMapping("/gallery")
	public ModelAndView getGallery() {
		ModelAndView mav = new ModelAndView("gallery");
		return mav;
	}

	@RequestMapping("/gallery/upload")
	public ModelAndView getUpload() {
		ModelAndView mav = new ModelAndView("upload");
		mav.addObject("postObj", new Post());
		mav.addObject("picObj", new Picture());
		return mav;
	}

	@RequestMapping(value = "/gallery/processUpload", method = RequestMethod.POST)
	public ModelAndView postUpload(@RequestParam("description") String description,
			@RequestParam("file") MultipartFile file, @ModelAttribute("picObj") Picture picObj,
			RedirectAttributes redir) {
		ModelAndView mav = new ModelAndView("redirect:/gallery");
		if (!file.isEmpty()) {
			System.out.println(file.getOriginalFilename());
			System.out.println(description);
			String rootPath = "\\CaseStudySIslam\\WebContent\\images\\uploads";
			System.out.println(rootPath);
			System.out.println(File.separator);
			File dir = new File(rootPath);
			if (!dir.exists())
				dir.mkdirs();
			String name = new BCrpytPasswordEncoderEdited().encode(file.getOriginalFilename());
			System.out.println("in controller: " + name);
			System.out.println(name.length());
//			File f = new File(rootPath+File.separator+name);
			
		}
		return mav;
	}

	@RequestMapping("/subscriber")
	public ModelAndView getSubscriber() {
		System.out.println("In the subs!");
		;
		ModelAndView mav = new ModelAndView("subscriber");
		return mav;
	}

	@RequestMapping("/contactus")
	public ModelAndView getContact() {
		System.out.println("In contact!");
		ModelAndView mav = new ModelAndView("contact");
		return mav;
	}

	@RequestMapping("/admin")
	public ModelAndView getAdmin() {
		System.out.println("You are an Admin!");
		ModelAndView mav = new ModelAndView("admin");
		return mav;
	}

	@RequestMapping("/all")
	public ModelAndView getSudo() {
		System.out.println("You are a SUDO!");
		ModelAndView mav = new ModelAndView("all");
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
