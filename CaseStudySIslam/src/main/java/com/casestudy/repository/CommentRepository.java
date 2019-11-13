package com.casestudy.repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
//	void addComment(Comment comment);
	void deleteCommentById(long id);
	Comment findCommentById(long id);

	@Query(value = "SELECT * FROM COMMENTS c WHERE c.post_id = ?1 and c.author_credential_id = ?2", nativeQuery = true)
	List<Comment> findCommentsByPostIdAndAuthorId(long id1, long id2);

	@Query(value = "SELECT c.author_credential_id, count(*) FROM COMMENTS c WHERE c.post_id = ?1 group by c.author_credential_id order by c.author_credential_id", nativeQuery = true)
	HashMap<BigInteger, Integer> findNumberOfCommentsByPostId(long id);
}
