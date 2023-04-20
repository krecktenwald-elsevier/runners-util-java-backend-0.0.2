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
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.RouteDTO;
import com.krecktenwald.runnersutil.domain.entities.Route;
import com.krecktenwald.runnersutil.repositories.RouteRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/routes")
public class RouteController {

	private final RouteRepository routeRepository;

	@Autowired
	private DTOMapper dtoMapper;

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
	public ResponseEntity<Route> createRoute(@RequestBody @Valid RouteDTO routeDTO) throws URISyntaxException {
		Route route = dtoMapper.map(routeDTO);
		route.setRouteId(String.format("route_%s", UUID.randomUUID()));
		route.setCreateDate(new Date());
		Route savedRoute = routeRepository.save(route);
		return ResponseEntity.created(new URI("/routes/" + savedRoute.getRouteId())).body(savedRoute);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Route> updateRoute(@PathVariable String id, @RequestBody RouteDTO routeDTO) {
		Route currentRoute = routeRepository.findById(id).orElseThrow(RuntimeException::new);

		currentRoute.setName(routeDTO.getName());
		currentRoute.setDistance(routeDTO.getDistance());
		currentRoute.setUpdateDate(new Date());

		currentRoute = routeRepository.save(dtoMapper.map(routeDTO));

		return ResponseEntity.ok(currentRoute);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Route> deleteRoute(@PathVariable String id) {
		routeRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
