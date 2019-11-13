package com.casestudy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casestudy.model.Credential;
import com.casestudy.model.User;
import com.casestudy.service.CredentialService;

@Controller
@Transactional
public class UserController {

	@Autowired
	CredentialService credentialService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(sdf, false));
	}

	@RequestMapping(value = "/user/{userId:.+}", method = RequestMethod.GET)
	public ModelAndView returnUserInfo(@PathVariable("userId") String email) {
		ModelAndView mav = null;
		mav = new ModelAndView("user");
		System.out.println(email);
		Credential credential = credentialService.findCredentialByEmail(email);
		System.out.println("*******************" + credential.getEmail());
		User user = credential.getUser();
		mav.addObject("credential", credential);
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(value = "/user/edit/{userId:.+}", method = RequestMethod.GET)
	public ModelAndView editUserInfo(@PathVariable("userId") String email,
			@ModelAttribute("userCredentialFormObj") Credential cred, @ModelAttribute("userFormObj") User u) {
		ModelAndView mav = null;
		mav = new ModelAndView("useredit");
		Credential credential = credentialService.findCredentialByEmail(email);
		User user = credential.getUser();
		mav.addObject("userCredentialFormObj", credential);
		mav.addObject("userObjForm", user);
		mav.addObject("action", "update");
		// mav.addObject("userObj", new Login());
		return mav;
	}

	@RequestMapping(value = "/updateUserCredential", method = RequestMethod.POST)
	public ModelAndView updateUserInfo(@Valid @ModelAttribute("userCredentialFormObj") Credential cred,
			BindingResult br1, @Valid @ModelAttribute("userFormObj") User u, BindingResult br2,
			@RequestParam("confPassword") String confPassword, RedirectAttributes redir) {
		ModelAndView mav = null;
		mav = new ModelAndView("redirect:/user/"+cred.getEmail());
		Credential credential = credentialService.findCredentialByEmail(cred.getEmail());
		User user = credential.getUser();
		if (br1.hasErrors() || br2.hasErrors()) {
			if (br1.hasErrors() && br2.hasErrors())
				redir.addFlashAttribute("message", "Name and password must not be empty");
			else if (br1.hasErrors())
				redir.addFlashAttribute("message", "Password must not be empty");
			else if (br2.hasErrors())
				redir.addFlashAttribute("message", "Name must not be empty");
			return new ModelAndView("redirect:/user/edit/"+credential.getEmail());
		}
		else if (!cred.getPassword().equals(confPassword)) {
			redir.addFlashAttribute("message", "Password must be the same");
			return new ModelAndView("redirect:/user/edit/"+credential.getEmail());
		}
		user.setName(u.getName());
		credential.setPassword(new BCryptPasswordEncoder().encode(cred.getPassword()));
		// mav.addObject("userObj", new Login());
		return mav;
	}

	@RequestMapping(value = "/user/delete/{userId:.+}", method = RequestMethod.POST)
	public ModelAndView delete(@PathVariable("userId") String email, RedirectAttributes redirect) {

		System.out.println("delete mapping");
		credentialService.deleteCredentialByEmail(email);
		redirect.addFlashAttribute("message", "user successfully deleted");
		SecurityContextHolder.getContext().setAuthentication(null);
		return new ModelAndView("redirect:/landing");
	}

}
