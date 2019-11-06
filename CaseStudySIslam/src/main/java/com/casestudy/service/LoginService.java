package com.casestudy.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casestudy.model.Credential;
import com.casestudy.repository.LoginRepository;

@Transactional
@Service
public class LoginService {
	@Autowired
	LoginRepository loginRepository;
	
//	@Autowired
//	UserRepository userRepository;
	
	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}
	
	public Credential getLoginbyEmail(String email) {
		return loginRepository.findByEmail(email);
	}
	
	
	public boolean addCredential(Credential cred) {
			
		return loginRepository.save(cred) != null;
	}
	
	public boolean updateCredential(Credential credential) {
		return loginRepository.save(credential) != null;
	}
	
	public void deleteCredential(Credential credential) {
		loginRepository.delete(credential);
	}
	public List<Credential> getAllCredential(){
		List<Credential> list = new ArrayList<>();
		loginRepository.findAll().forEach((e)->{
			list.add(e);
		});
		return list;
	}
//	
//	@Query("SELECT * from login where (From_date BETWEEN AND '2013-01-09')")
//	public List<Login> findLoginBetweenDateOfBirth(Date dob1, Date dob2){
//		List<Login> users = new ArrayList<>();
//		
//		return users;
//		loginRepository.findLoginBetweenDateOfBirth(dob1, dob2).forEach((e) -> {
//			System.out.println(e.toString());
//		});
//		return loginRepository.findLoginBetweenDateOfBirth(dob1, dob2);
//	}
	
}
