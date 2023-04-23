package com.krecktenwald.runnersutil.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.RouteDTO;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.UserDTO;
import com.krecktenwald.runnersutil.domain.entities.Route;
import com.krecktenwald.runnersutil.domain.entities.Run;
import com.krecktenwald.runnersutil.domain.entities.User;
import com.krecktenwald.runnersutil.repositories.RouteRepository;
import com.krecktenwald.runnersutil.repositories.RunRepository;
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
	public Set<UserDTO> getUsers() {
		Set<UserDTO> userDTOs = new HashSet<>();
		for(User user : userRepository.findAll()){
			userDTOs.add(convertUserToDTO(user));
		}

		return userDTOs;
	}

	@GetMapping("/{id}")
	public UserDTO getUser(@PathVariable String id) {
		User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
		return convertUserToDTO(user);
	}

	@PostMapping()
	public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) throws URISyntaxException {
		User user = dtoMapper.map(userDTO);
		user.setUserId(String.format("user_%s", UUID.randomUUID()));
		user.setCreateDate(new Date());

		User savedUser = userRepository.save(user);
		UserDTO savedUserDTO = convertUserToDTO(savedUser);

		return ResponseEntity.created(new URI("/api/users/" + savedUser.getUserId())).body(savedUserDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
		User currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);

		currentUser.setUpdateDate(new Date());
		currentUser = userRepository.save(currentUser);
		UserDTO savedUserDTO = convertUserToDTO(currentUser);
		return ResponseEntity.ok(savedUserDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable String id) {
		userRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}


	private UserDTO convertUserToDTO(User user) {
		UserDTO userDTO = dtoMapper.map(user);

		Set<String> runIDs = new HashSet<>();
		if(user.getRuns() != null){
			for(Run run : user.getRuns()){
				runIDs.add(run.getRunId());
			}
		}
		userDTO.setRunIDs(runIDs);

		Set<String> createdRouteIDs = new HashSet<>();
		if(user.getCreatedRoutes() != null){
			for(Route route : user.getCreatedRoutes()){
				createdRouteIDs.add(route.getRouteId());
			}
		}
		userDTO.setCreatedRouteIDs(createdRouteIDs);
		return userDTO;
	}
}
