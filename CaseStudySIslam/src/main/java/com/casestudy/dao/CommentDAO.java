package com.casestudy.dao;

import com.casestudy.model.Comment;

import antlr.collections.List;

public interface CommentDAO {
	void addComment(Comment comment);
	void deleteCommentById(long id);
	
}
