package com.casestudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.dao.CommentDAO;
import com.casestudy.model.Comment;
import com.casestudy.repository.CommentRepository;

@Service("commentService")
public class CommentService implements CommentDAO {
	@Autowired
	CommentRepository commentRepository;

	@Override
	public void deleteCommentById(long id) {
		// TODO Auto-generated method stub
		commentRepository.deleteById(id);
	}

	@Override
	public void addComment(Comment comment) {
		commentRepository.save(comment);
	}
	
	

}
