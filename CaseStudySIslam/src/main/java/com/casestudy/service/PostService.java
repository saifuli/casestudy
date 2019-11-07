package com.casestudy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.dao.PostDAO;
import com.casestudy.model.Post;
import com.casestudy.repository.CredentialRepository;
import com.casestudy.repository.PostRepository;



@Service("postService")
public class PostService implements PostDAO {
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CredentialRepository credentialRepository;
	
	@Override
	public void addPost(Post post) {
		postRepository.save(post);
		System.out.println("after adding post");
	}

	@Override
	public void deletePost(Post post) {
		postRepository.delete(post);
		
	}

	@Override
	public List<Post> findAllPostsByCredentialId(Long id) {
		// TODO Auto-generated method stub
		Iterable<Post> posts = findAllPosts();
		List<Post> postsList = new ArrayList<>();
		posts.forEach(post -> {
			if (post.getAuthor().getId() == id)
				postsList.add(post);
		});
		return postsList;
	}
	
	public List<Post> findAllPosts(){
		Iterable<Post> posts = postRepository.findAll();
		List<Post> postsList = new ArrayList<>();
		posts.forEach(postsList::add);
		return postsList;
	}
}
