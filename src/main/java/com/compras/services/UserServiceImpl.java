package com.compras.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compras.models.entities.User;
import com.compras.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	@Override
	@Transactional
	public Optional<User> update(User user, Long id) {
		Optional<User> userOptional = this.findById(id);
		User userSave = null;
		if (userOptional.isPresent()) {
			User userDb = userOptional.orElseThrow();
			userDb.setUsername(user.getUsername());
			userDb.setPassword(user.getPassword());
			userDb.setEmail(user.getEmail());
			userSave = this.save(userDb);
		}
		return Optional.ofNullable(userSave);
	}

	@Override
	@Transactional
	public void remove(Long id) {
		repository.deleteById(id);
	}

}
