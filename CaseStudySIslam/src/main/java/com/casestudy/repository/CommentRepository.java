package com.casestudy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
//	void addComment(Comment comment);
	void deleteCommentById(long id);
//	Comment findComment(Comment comment);
}
