package com.casestudy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import com.casestudy.model.Credential;

@Repository
public interface CredentialRepository extends CrudRepository<Credential, Long> {
	Credential findCredentialById(long id);
	
	Credential findCredentialByEmail(String email);

	Credential findCredentialByUsername(String username);
	
	void deleteCredentialByEmail(String email);

}
