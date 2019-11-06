package com.casestudy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.model.Credential;
import com.casestudy.model.User;

@Repository
public interface CredentialRepository extends CrudRepository<Credential, Long>{
	Credential findByEmail(String email);


}
