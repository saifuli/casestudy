package com.casestudy.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

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

	@Override
	public Comment findCommentById(long id) {
		// TODO Auto-generated method stub
		return commentRepository.findCommentById(id);
	}
	
	public List<Comment> findCommentsByPostIdAndAuthorId(long id1, long id2) {
		return commentRepository.findCommentsByPostIdAndAuthorId(id1, id2);
	}

	@Override
	public HashMap<BigInteger, Integer> findNumberOfCommentsByPostId(long id) {
		// TODO Auto-generated method stub
		return commentRepository.findNumberOfCommentsByPostId(id);
	}
	

}
