package com.compras.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compras.models.entities.User;

public interface UserRepository  extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);

}
