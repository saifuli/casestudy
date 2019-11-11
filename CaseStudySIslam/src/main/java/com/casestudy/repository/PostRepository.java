package com.casestudy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

}
