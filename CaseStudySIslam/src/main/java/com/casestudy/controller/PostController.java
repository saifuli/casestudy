package com.casestudy.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casestudy.dao.CommentDAO;
import com.casestudy.dao.PictureDAO;
import com.casestudy.dao.PostDAO;
import com.casestudy.model.Comment;
import com.casestudy.model.Credential;
import com.casestudy.model.Picture;
import com.casestudy.model.Post;
import com.casestudy.service.CredentialService;

@Controller
@Transactional
public class PostController {

	@Autowired
	PostDAO postDAO;
	
	@Autowired
	CredentialService credentialService;
	
	@Autowired
	PictureDAO pictureDAO;
	
	@Autowired
	CommentDAO commentDAO;
	
	@RequestMapping(value = "/gallery/{picName}", method = RequestMethod.GET)
	public ModelAndView getPostInfo(@PathVariable("picName") String picName, Principal principal, Model model) {
		ModelAndView mav = null;
		mav = new ModelAndView("post");
		Picture picture = pictureDAO.findPictureByName(picName);
		Post post = postDAO.findPostById(picture.getId());
		if (model.getAttribute("viewsUpdate") == null)
			post.setViews(post.getViews()+1);
		Credential credential = null;

		mav.addObject("post", postDAO.findPostById(picture.getId()));
		mav.addObject("picture", picture);
		if (principal != null) {
			credential = credentialService.findCredentialByEmail(principal.getName());
			Set<String> authorities = new HashSet<String>(Arrays.asList(credential.getAuthorities().stream().map(a-> a.getAuthority()).toArray(String[]::new)));
			if (authorities.contains("ROLE_ADMIN")) {
				System.out.println("CONTAINS ADMIN");
				mav.addObject("role", "admin");
			}
			
			mav.addObject("credential", credential);
			if (model.getAttribute("action") != null && model.getAttribute("action").equals("edit")) {
				Optional<Comment> optional = commentDAO.findCommentById(Long.parseLong((String) model.getAttribute("commentId")));
				if (optional.isPresent()) {
					Comment comment = optional.get();
					if (credential.getUser().getId() == comment.getAuthor().getId()) {
						mav.addObject("action", "edit");
						mav.addObject("comment", comment);
					}
				}
			}
				
		}
		return mav;
	}
	
	@RequestMapping(value = "/gallery/edit/{picName}", method = RequestMethod.GET)
	public ModelAndView editPostInfo(@PathVariable("picName") String picName) {
		ModelAndView mav = null;
		mav = new ModelAndView("editPost");
		Picture picture = pictureDAO.findPictureByName(picName);

		mav.addObject("post", postDAO.findPostById(picture.getId()));
		mav.addObject("picture", picture);
		mav.addObject("action", "edit");
		return mav;
	}
	
	@RequestMapping(value = "/gallery/edit/{picName}", method = RequestMethod.POST)
	public ModelAndView submitEditPostInfo(@PathVariable("picName") String picName,
			@ModelAttribute("post") Post post, @ModelAttribute("picture") Picture picture, RedirectAttributes redir) {
		ModelAndView mav = null;
		mav = new ModelAndView("redirect:/gallery/"+picName);
		redir.addFlashAttribute("redirect", "redirect");
		Post p = postDAO.findPostById(pictureDAO.findPictureByName(picName).getId());
		p.setDescription(post.getDescription());
		
		redir.addFlashAttribute("viewsUpdate", "viewsUpdate");
		return mav;
	}
	
	@RequestMapping(value = "/gallery/delete/{picName}", method = RequestMethod.POST)
	public ModelAndView deletePost(@PathVariable("picName") String picName, Principal principal) {
		Picture picture = pictureDAO.findPictureByName(picName);
		postDAO.deletePostById(picture.getId());
		
		
		return new ModelAndView("redirect:/gallery");
	}
}
