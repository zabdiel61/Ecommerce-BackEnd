package com.compras.services;

import java.util.List;
import java.util.Optional;

import com.compras.models.dto.UserDto;
import com.compras.models.entities.User;

public interface UserService {

	List<User> findAll();

	Optional<User> findById(Long id);

	UserDto save(User user);

	Optional<UserDto> update(User user, Long id);

	void remove(Long id);

}
