package com.casestudy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.dao.UserDAO;
import com.casestudy.model.User;
import com.casestudy.repository.UserRepository;

@Service("userService")
public class UserService implements UserDAO {
	@Autowired
	UserRepository userRepository;

	@Override
	public void addUser(User user) {
		userRepository.save(user);

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userRepository.delete(user);

	}

	@Override
	public User findUserById(long id) {
		// TODO Auto-generated method stub
		return userRepository.findUserById(id);
	}

}
