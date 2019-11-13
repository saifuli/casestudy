package com.casestudy.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.casestudy.model.User;

public interface UserDAO {
	void addUser(User user);
	
	void deleteUser(User user);
	
	User findUserById(long id);

	
}
