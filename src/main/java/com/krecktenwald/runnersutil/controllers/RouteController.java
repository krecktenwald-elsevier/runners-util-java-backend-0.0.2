package com.krecktenwald.runnersutil.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
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
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.CreateRouteDTO;
import com.krecktenwald.runnersutil.domain.entities.Route;
import com.krecktenwald.runnersutil.domain.entities.User;
import com.krecktenwald.runnersutil.repositories.RouteRepository;
import com.krecktenwald.runnersutil.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/routes")
public class RouteController {

	private final RouteRepository routeRepository;

	@Autowired
	private DTOMapper dtoMapper;

	@Autowired
	private UserRepository userRepository;

	public RouteController(RouteRepository routeRepository) {
		this.routeRepository = routeRepository;
	}

	@GetMapping
	public List<Route> getRoutes() {
		return routeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Route getRoute(@PathVariable String id) {
		return routeRepository.findById(id).orElseThrow(RuntimeException::new);
	}

	@PostMapping()
	public ResponseEntity<Route> createRoute(@RequestBody @Valid CreateRouteDTO createRouteDTO) throws URISyntaxException {
		Route route = dtoMapper.map(createRouteDTO);
		route.setRouteId(String.format("route_%s", UUID.randomUUID()));
		route.setCreateDate(new Date());

		if(createRouteDTO.getName() != null){
			route.setName(createRouteDTO.getName());
		}

		if(createRouteDTO.getDistance() != null){
			route.setDistance(createRouteDTO.getDistance());
		}

		if(createRouteDTO.getCreatorUserId() != null){
			User creatorUser = userRepository.findById(createRouteDTO.getCreatorUserId()).orElseThrow(RuntimeException::new);
			route.setCreator(creatorUser);
			//route.setUsersWithAccess(new HashSet<>(Collections.singletonList(createRouteDTO.getCreator())));
		}

		Route savedRoute = routeRepository.save(route);

		return ResponseEntity.created(new URI("/routes/" + savedRoute.getRouteId())).body(route);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Route> updateRoute(@PathVariable String id, @RequestBody CreateRouteDTO createRouteDTO) {
		Route currentRoute = routeRepository.findById(id).orElseThrow(RuntimeException::new);

		if(createRouteDTO.getName() != null){
			currentRoute.setName(createRouteDTO.getName());
		}

		if(createRouteDTO.getDistance() != null){
			currentRoute.setDistance(createRouteDTO.getDistance());
		}

		if(createRouteDTO.getCreator() != null){
			currentRoute.setCreator(createRouteDTO.getCreator());
		}

		currentRoute.setUpdateDate(new Date());

		currentRoute = routeRepository.save(dtoMapper.map(createRouteDTO));

		return ResponseEntity.ok(currentRoute);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Route> deleteRoute(@PathVariable String id) {
		routeRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
