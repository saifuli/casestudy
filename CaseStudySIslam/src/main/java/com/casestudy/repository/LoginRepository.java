package com.casestudy.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.model.Credential;


@Repository
public interface LoginRepository extends CrudRepository<Credential, Long>{
	Credential findByEmail(String email);
	
	
	
	
//	@Query(value = "SELECT * from userlogins.login l where dateOfBirth BETWEEN ?1 AND ?2", nativeQuery = true)
//	List<Login> findLoginBetweenDateOfBirth(Date dob1, Date dob2);
	
}
