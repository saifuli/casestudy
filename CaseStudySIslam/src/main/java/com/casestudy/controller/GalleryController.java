package com.casestudy.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import com.casestudy.model.Picture;
import com.casestudy.model.Post;
import com.casestudy.model.User;
import com.casestudy.repository.CredentialRepository;

@Controller
@Transactional
public class GalleryController {
	@Autowired
	Environment environment;
	
	@Autowired
	CredentialRepository credentialRepository;
	
	@Autowired
	UserDAO userDAO;

	@Autowired
	PictureDAO pictureDAO;
	
	@Autowired
	PostDAO postDAO;
	
	@RequestMapping("/gallery")
	public ModelAndView getGallery() {
		ModelAndView mav = new ModelAndView("gallery");
		List<Post> posts = postDAO.findAllPosts();
		mav.addObject("posts", posts);
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
	public ModelAndView postUpload(String description,
			@RequestParam("file") MultipartFile file, Principal principal,
			RedirectAttributes redir) {
		ModelAndView mav = new ModelAndView("redirect:/gallery");
		Picture picture = new Picture();
		Post post = new Post();

		if (!file.isEmpty()) {

			try {
				File dir = new File(environment.getRequiredProperty("filepath"));
				if (!dir.exists())
					dir.mkdirs();
				byte[] fileBytes = file.getBytes();
				String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
				String name = new BCrpytPasswordEncoderEdited().encode(file.getOriginalFilename());

//				String path = rootPath + File.separator + name + ext;
				String path = environment.getRequiredProperty("filepath")+File.separator+name+ext;

				picture.setName(name); picture.setPath(path); picture.setPost(post);
				
				User user = credentialRepository.findCredentialByEmail(principal.getName()).getUser();
				user.getPosts().add(post);
				user.setNumOfPosts(user.getNumOfComments()+1);

				post.setDescription(description); post.setTimestamp(new Date());
				
				post.setPicture(picture);				
				post.setAuthor(user);

				postDAO.addPost(post);
				

				File f = new File(path);
				BufferedOutputStream bostream = new BufferedOutputStream(new FileOutputStream(f));
				bostream.write(fileBytes);
				bostream.close();

				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("File " + file.getOriginalFilename() + " " + picture.getName() + " not found.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("File could not write to server.");
			}
		}
		redir.addFlashAttribute("post", post);
		return mav;
	}
}
