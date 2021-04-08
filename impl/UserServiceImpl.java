package com.claim.kidsstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.kidsstore.model.User;
import com.claim.kidsstore.repository.UserRepository;
import com.claim.kidsstore.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository repository;
	
	@Autowired
	public UserServiceImpl(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Optional<User> findById(long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public Optional<User> findEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findEmail(email);
	}

	@Override
	public Optional<User> login(String email, String password) {
		// TODO Auto-generated method stub
		return repository.login(email, password);
	}

	@Override
	public List<User> findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return repository.findByLastName(lastName);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public void resetPassword(String password, Long id) {
		// TODO Auto-generated method stub
		repository.findById(id).ifPresent(a->{
			a.setPassword(password);
		});
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		repository.save(user);
	}

	@Override
	public List<User> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
