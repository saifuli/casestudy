package com.casestudy.controller;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casestudy.dao.CommentDAO;
import com.casestudy.dao.PictureDAO;
import com.casestudy.dao.PostDAO;
import com.casestudy.dao.UserDAO;
import com.casestudy.model.Comment;
import com.casestudy.model.Credential;
import com.casestudy.model.Post;
import com.casestudy.model.User;
import com.casestudy.service.CredentialService;
import com.casestudy.service.UserService;

@Controller
@Transactional
public class CommentController {
	@Autowired
	CredentialService credentialService;

	@Autowired
	PostDAO postDAO;

	@Autowired
	PictureDAO pictureDAO;

	@Autowired
	CommentDAO commentDAO;
	
	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/gallery/{picName}/submitcomment", method = RequestMethod.POST)
	public ModelAndView submitComment(String postComment, @PathVariable("picName") String picName, Principal principal,
			RedirectAttributes redir) {
		ModelAndView mav = new ModelAndView("redirect:/gallery/" + picName);
		Post post = postDAO.findPostById(pictureDAO.findPictureByName(picName).getId());
		Comment comment = new Comment();
		Credential credential = credentialService.findCredentialByEmail(principal.getName());
		User user = credential.getUser();
		comment.setComment(postComment);
		comment.setPost(post);
		comment.setTimestamp(new Date());

		comment.setAuthor(credential.getUser());
		post.getComments().add(comment);
		user.getComments().add(comment);
		user.setNumOfComments(user.getNumOfComments() + 1);
		redir.addFlashAttribute("viewsUpdate", "viewsUpdate");
		return mav;
	}

	@RequestMapping(value = "/gallery/{picName}/editcomment/{commentId}", method = RequestMethod.GET)
	public ModelAndView editComment(String comment, @PathVariable("picName") String picName,
			@PathVariable("commentId") String commentId, RedirectAttributes redir) {
		ModelAndView mav = new ModelAndView("redirect:/gallery/" + picName);
		redir.addFlashAttribute("action", "edit");
		redir.addFlashAttribute("commentId", commentId);
		return mav;
	}

	@RequestMapping(value = "/gallery/{picName}/editcomment/{commentId}/submit", method = RequestMethod.POST)
	public ModelAndView submitEditedComment(String newComment, @PathVariable("picName") String picName,
			@PathVariable("commentId") String commentId, RedirectAttributes redir) {
		ModelAndView mav = new ModelAndView("redirect:/gallery/{picName}/");
		Comment comment = commentDAO.findCommentById(Long.parseLong(commentId));
		
			String oldComment = comment.getComment();
			Post post = postDAO.findPostById(pictureDAO.findPictureByName(picName).getId());
			post.getComments().forEach(c -> {
				if (c.getComment().equals(oldComment))
					c.setComment(newComment);
			});
			comment.setComment(newComment);

			redir.addFlashAttribute("viewsUpdate", "viewsUpdate");
		
		return mav;
	}

	@RequestMapping(value = "/gallery/{picName}/deletecomment/{commentId}", method = RequestMethod.POST)
	public ModelAndView deleteComment(@PathVariable("picName") String picName,
			@PathVariable("commentId") String commentId, RedirectAttributes redir) {
		ModelAndView mav = new ModelAndView("redirect:/gallery/" + picName);

		Post post = postDAO.findPostById(pictureDAO.findPictureByName(picName).getId());
		Comment comment = commentDAO.findCommentById(Long.parseLong(commentId));
		User user = userDAO.findUserById(comment.getAuthor().getId());
		System.out.println(commentId);
		post.getComments().removeIf(c -> c.getId() == Long.parseLong(commentId));
		user.setNumOfComments(user.getNumOfComments()-1);
		commentDAO.deleteCommentById(Long.parseLong(commentId));
		redir.addFlashAttribute("viewsUpdate", "viewsUpdate");
		return mav;
	}
}
