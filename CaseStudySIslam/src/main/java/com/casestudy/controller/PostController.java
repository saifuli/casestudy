package com.casestudy.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.casestudy.dao.PictureDAO;
import com.casestudy.dao.PostDAO;
import com.casestudy.model.Picture;
import com.casestudy.model.Post;
import com.casestudy.service.CredentialService;
import com.sun.org.glassfish.gmbal.Description;

@Controller
@Transactional
public class PostController {

	@Autowired
	PostDAO postDAO;
	
	@Autowired
	CredentialService credentialService;
	
	@Autowired
	PictureDAO pictureDAO;
	
	@RequestMapping(value = "/gallery/edit/{picName}", method = RequestMethod.GET)
	public ModelAndView editUserInfo(@PathVariable("picName") String picName) {
		ModelAndView mav = null;
		mav = new ModelAndView("editPost");
		Picture picture = pictureDAO.findPictureByName(picName);
		System.out.println(picture.toString());
		System.out.println(postDAO.findPostById(picture.getId()).toString());
		mav.addObject("post", postDAO.findPostById(picture.getId()));
		mav.addObject("picture", picture);
		mav.addObject("action", "edit");
		return mav;
	}
	
	@RequestMapping(value = "/gallery/edit/{picName}", method = RequestMethod.POST)
	public ModelAndView submitEditUserInfo(@PathVariable("picName") String picName,
			@ModelAttribute("post") Post post, @ModelAttribute("picture") Picture picture) {
		ModelAndView mav = null;
		mav = new ModelAndView("redirect:/gallery");
		System.out.println(post.toString());
		Post p = postDAO.findPostById(pictureDAO.findPictureByName(picName).getId());
		p.setDescription(post.getDescription());
		postDAO.addPost(p);
		return mav;
	}
	
	@RequestMapping(value = "/gallery/delete/{picName}", method = RequestMethod.POST)
	public ModelAndView deletePost(@PathVariable("picName") String picName, Principal principal) {
		System.out.println("---------------------------------------------------------- delete");
		System.out.println(picName);

		Picture picture = pictureDAO.findPictureByName(picName);
		postDAO.deletePostById(picture.getId());
		
		
		return new ModelAndView("redirect:/gallery");
	}
}
