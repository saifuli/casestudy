package com.casestudy.controller;
//package com.casestudy.saiful.islam.controller;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.propertyeditors.CustomDateEditor;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.profile.model.Login;
//import com.profile.service.loginService.LoginService;
//
//@Controller
//public class UserController {
//
//	LoginService loginService;
//
//	public UserController(LoginService loginService) {
//		this.loginService = loginService;
//	}
//
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(sdf, false));
//	}
//
//	@RequestMapping(value = "/users", method = RequestMethod.GET)
//	public ModelAndView returnUserList() {
//		ModelAndView mav = null;
//		loginService.getAllLogin().forEach((e) -> {
//			System.out.println(e.toString());
//		});
//
//		mav = new ModelAndView("users");
//		mav.addObject("usersList", loginService.getAllLogin());
//		return mav;
//	}
//
//	@RequestMapping(value = "/users/filter", method = RequestMethod.POST)
//	public ModelAndView returnUserListBetweenDates(
//			@RequestParam("date1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
//			@RequestParam("date2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2) {
//		ModelAndView mav = null;
//		System.out.println("In between");
//		System.out.println("Date1: " + date1);
//		System.out.println("Date1: " + date2);
//		if (date1 == null || date2 == null)
//			return new ModelAndView("redirect:/users");
//		mav = new ModelAndView("users");
//		List<Login> filteredList = loginService.findByStartDateBetween(date1, date2);
//		if (filteredList.size() == 0)
//			mav.addObject("message", "Results not found for dates between " + date1 + " and " + date2);
//		else {
//			mav.addObject("usersList", filteredList);
//			mav.addObject("size", "Found " + filteredList.size());
//		}
//		mav.addObject("date1", date1);
//		mav.addObject("date2", date2);
//		return mav;
//	}
//
//	@RequestMapping(value = "/user", method = RequestMethod.GET)
//	public ModelAndView returnUserInfo(@RequestParam("u") String email) {
//		ModelAndView mav = null;
//		mav = new ModelAndView("user");
//		Login login = loginService.getLoginbyEmail(email);
//		mav.addObject("userInfo", login);
//		return mav;
//	}
//
//	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
//	public ModelAndView editUserInfo(@RequestParam("u") String email,
//			@ModelAttribute("userCredentialFormObj") Login login) {
//		ModelAndView mav = null;
//		mav = new ModelAndView("userCredentialForm");
//		mav.addObject("userCredentialFormObj", loginService.getLoginbyEmail(email));
//		mav.addObject("action", "update");
//		// mav.addObject("userObj", new Login());
//		return mav;
//	}
//
//	@RequestMapping(value = "/user/delete", method = RequestMethod.GET)
//	public ModelAndView delete(@RequestParam("u") String email, RedirectAttributes redirect) {
//
//		System.out.println("delete mapping");
//		loginService.deleteLogin(loginService.getLoginbyEmail(email));
//		redirect.addFlashAttribute("message", "user successfully deleted");
//		return new ModelAndView("redirect:/users");
//	}
//
//}
