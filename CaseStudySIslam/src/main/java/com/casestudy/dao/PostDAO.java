package com.casestudy.dao;

import java.util.List;

import com.casestudy.model.Post;



public interface PostDAO {
	void addPost(Post post);
	void deletePost(Post post);
	void deletePostById(long id);
	Post findPostById(long id);
	List<Post> findAllPostsByCredentialId(Long id);
	List<Post> findAllPosts();
	
}
