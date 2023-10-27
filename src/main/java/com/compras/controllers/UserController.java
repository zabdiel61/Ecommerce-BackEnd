package com.compras.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compras.models.dto.UserDto;
import com.compras.models.entities.User;
import com.compras.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public List<User> list() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Optional<User> userOptional = service.findById(id);

		if (userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
		if (result.hasErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return validation(result);
		}
		Optional<UserDto> userOptional = service.update(user, id);
		if (userOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		Optional<User> userOptional = service.findById(id);

		if (userOptional.isPresent()) {
			service.remove(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errors = new HashMap<>();

		result.getFieldErrors().forEach(err -> {
			errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}

}
