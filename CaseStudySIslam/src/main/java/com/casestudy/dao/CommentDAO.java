package com.casestudy.dao;

import java.util.Optional;

import com.casestudy.model.Comment;

import antlr.collections.List;

public interface CommentDAO {
	void addComment(Comment comment);
	void deleteCommentById(long id);
	Optional<Comment> findCommentById(long id);
}
