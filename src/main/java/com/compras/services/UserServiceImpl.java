package com.compras.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compras.models.dto.UserDto;
import com.compras.models.dto.mapper.UserMapper;
import com.compras.models.entities.Role;
import com.compras.models.entities.User;
import com.compras.repositories.RoleRepository;
import com.compras.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

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
	public UserDto save(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
		List<Role> roles = new ArrayList<>();
		if (roleOptional.isPresent()) {
			roles.add(roleOptional.orElseThrow());
		}
		user.setRoles(roles);
		User userSave = repository.save(user);

		return userMapper.toUserDto(userSave);
	}

	@Override
	@Transactional
	public Optional<UserDto> update(User user, Long id) {
		Optional<User> userOptional = this.findById(id);
		User userSave = null;

		if (userOptional.isPresent()) {
			User userDb = userOptional.orElseThrow();
			userDb.setUsername(user.getUsername());
			userDb.setPassword(user.getPassword());
			userDb.setEmail(user.getEmail());
			userSave = repository.save(userDb);
		}

		return Optional.ofNullable(userMapper.toUserDto(userSave));
	}

	@Override
	@Transactional
	public void remove(Long id) {
		repository.deleteById(id);
	}

}
