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
	Environment environment;
	
	@Autowired
	CredentialRepository credentialRepository;
	
	@Autowired
	UserDAO userDAO;

	@Autowired
	PictureDAO pictureDAO;
	
	@Autowired
	PostDAO postDAO;

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
		Credential credential = credentialRepository.findCredentialByEmail(principal.getName());
		mav.addObject("credential", credential);
		return mav;
	}

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
	public ModelAndView postUpload(@RequestParam("description") String description,
			@RequestParam("file") MultipartFile file, Principal principal,
			RedirectAttributes redir) {
		ModelAndView mav = new ModelAndView("redirect:/gallery");
		Picture picture = new Picture();
		Post post = new Post();
		System.out.println();
		if (!file.isEmpty()) {
			System.out.println(file.getOriginalFilename());
			System.out.println(description);
			System.out.println(Paths.get(".").toAbsolutePath());
			String rootPath = "\\CaseStudySIslam\\WebContent\\images\\uploads";
			System.out.println(rootPath);
			System.out.println(File.separator);
			try {
				File dir = new File(environment.getRequiredProperty("filepath"));
				if (!dir.exists())
					dir.mkdirs();
				byte[] fileBytes = file.getBytes();
				String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
				String name = new BCrpytPasswordEncoderEdited().encode(file.getOriginalFilename());
				System.out.println("in controller: " + name);
				System.out.println(name.length());
//				String path = rootPath + File.separator + name + ext;
				String path = environment.getRequiredProperty("filepath")+File.separator+name+ext;
				System.out.println("path: " + path);
				
				picture.setName(name); picture.setPath(path); picture.setPost(post);
				
				User user = credentialRepository.findCredentialByEmail(principal.getName()).getUser();
				user.getPosts().add(post);

				post.setDescription(description); post.setTimestamp(new Date());
				
				post.setPicture(picture);				
				post.setAuthor(user);
//				picture.setPost(post);
				

//				System.out.println("adding picture: " + pictureDAO.addPicture(picture));
//				
//				Picture pic = pictureDAO.findPictureByName(picture.getName());
				post.setPicture(picture);
				picture.setPost(post);
				
				System.out.println(post.toString());
				System.out.println(picture.toString());
				System.out.println(user.toString());
				
				System.out.println("********************************* - 1");
				
				postDAO.addPost(post);
				
				System.out.println("********************************* - 2");
				File f = new File(path);
				BufferedOutputStream bostream = new BufferedOutputStream(new FileOutputStream(f));
				bostream.write(fileBytes);
				bostream.close();
				System.out.println("Saved file.");
				System.out.println(path);
				System.out.println(post.toString());
				
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
