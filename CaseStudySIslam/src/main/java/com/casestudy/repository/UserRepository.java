package com.casestudy.repository;

import org.springframework.data.repository.CrudRepository;

import com.casestudy.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
}
