package com.casestudy.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.casestudy.model.Credential;
import com.casestudy.repository.CredentialRepository;

@Service("userDetailsService")
public class CredentialService implements UserDetailsService{

//	@Autowired
//	UserDetailsDAO userDetailsDAO;
	
	@Autowired
	CredentialRepository credentialRepository;
	
	public boolean saveCredential(Credential cred) {
		credentialRepository.save(cred);
		return credentialRepository.findCredentialByEmail(cred.getEmail()) != null;
//		return userRepository.findByUsername(user.getUsername());
	}
	
	public Credential findCredentialByEmail(String email) {
		return credentialRepository.findCredentialByUsername(email);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(email + "*************************");
		Credential credential = credentialRepository.findCredentialByEmail(email);
		System.out.println(credential);
		UserBuilder builder = null;
		if (credential != null) {
			builder = User.withUsername(email);
			builder.password(credential.getPassword());
			
			String[] authorities = credential.getAuthorities().stream().map(a-> a.getAuthority()).toArray(String[]::new);
			builder.authorities(authorities);
		}
		else
			throw new UsernameNotFoundException("User Not Found!");
		return builder.build();
	}
	
//	public List<Credential> findAllCredentials(){
//        List<Credential> credentialsList = new ArrayList<>();
//        credentialRepository.findAll().forEach(credentialsList::add);
//        return credentialsList;
//    }

	

}
