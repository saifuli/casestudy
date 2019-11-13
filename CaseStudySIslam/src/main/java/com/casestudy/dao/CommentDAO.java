package com.casestudy.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.casestudy.model.Comment;



public interface CommentDAO {
	void addComment(Comment comment);

	void deleteCommentById(long id);

	Comment findCommentById(long id);
	
	List<Comment> findCommentsByPostIdAndAuthorId(long id1, long id2);
	
	HashMap<BigInteger, Integer> findNumberOfCommentsByPostId(long id);
}
