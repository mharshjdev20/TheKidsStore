package com.claim.kidsstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.claim.kidsstore.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("FROM User WHERE last_name = ?1")
	List<User> findByLastName(String lastName);
	
	@Query("FROM User WHERE last_name = ?1 or first_name = ?1")
	List<User> findByName(String name);

	@Query("FROM User WHERE email = ?1")
	Optional<User> findEmail(String email);

	@Query("FROM User WHERE email =?1 AND password = ?2")
	Optional<User> login(String email, String password);
	
	User findByEmail(String email);
	


}
