package com.krecktenwald.runnersutil.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krecktenwald.runnersutil.domain.dto.mapper.DTOMapper;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.UserDTO;
import com.krecktenwald.runnersutil.domain.entities.User;
import com.krecktenwald.runnersutil.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

	private final UserRepository userRepository;

	@Autowired
	DTOMapper dtoMapper;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable String id) {
		return userRepository.findById(id).orElseThrow(RuntimeException::new);
	}

	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) throws URISyntaxException {
		User user = dtoMapper.map(userDTO);
		user.setUserId(String.format("user_%s", UUID.randomUUID()));
		user.setCreateDate(new Date());
		User savedUser = userRepository.save(user);
		return ResponseEntity.created(new URI("/api/users/" + savedUser.getUserId())).body(savedUser);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
		User currentUser = userRepository.save(dtoMapper.map(userDTO));

		return ResponseEntity.ok(currentUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable String id) {
		userRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
