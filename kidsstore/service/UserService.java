package com.claim.kidsstore.service;

import java.util.List;
import java.util.Optional;

import com.claim.kidsstore.model.User;




public interface UserService {
	
	Optional<User> findById(long id);
	
	Optional <User> findEmail (String email);
	
	Optional <User> login (String email, String password);
	
	List<User> findByLastName (String lastName);
	
	List<User> findAll();
	
	void delete (long id);
	
	void resetPassword(String password, Long id);
	
	void save(User user);
	
	List<User> findByName (String name);
}
