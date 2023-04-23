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
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.CreateRouteDTO;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.RouteDTO;
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
	public ResponseEntity<RouteDTO> createRoute(@RequestBody @Valid RouteDTO routeDTO) throws URISyntaxException {
		Route route = dtoMapper.map(routeDTO);
		route.setRouteId(String.format("route_%s", UUID.randomUUID()));
		route.setCreateDate(new Date());

		if(routeDTO.getCreatorUserID() != null){
			User creatorUser = userRepository.findById(routeDTO.getCreatorUserID()).orElseThrow(RuntimeException::new);
			route.setCreator(creatorUser);
			//route.setUsersWithAccess(new HashSet<>(Collections.singletonList(createRouteDTO.getCreator())));
		}

		Route savedRoute = routeRepository.save(route);
		RouteDTO savedRouteDTO = dtoMapper.map(savedRoute);

		savedRouteDTO.setCreatorUserID(savedRoute.getCreator().getUserId());

		return ResponseEntity.created(new URI("/routes/" + savedRoute.getRouteId())).body(dtoMapper.map(savedRoute));
	}

	@PutMapping("/{id}")
	public ResponseEntity<RouteDTO> updateRoute(@PathVariable String id, @RequestBody RouteDTO routeDTO) {
		Route currentRoute = routeRepository.findById(id).orElseThrow(RuntimeException::new);

		if(routeDTO.getName() != null){
			currentRoute.setName(routeDTO.getName());
		}

		if(routeDTO.getDistance() != null){
			currentRoute.setDistance(routeDTO.getDistance());
		}

		if(routeDTO.getCreatorUserID() != null){
			User creatorUser = userRepository.findById(routeDTO.getCreatorUserID()).orElseThrow(RuntimeException::new);
			currentRoute.setCreator(creatorUser);
			//route.setUsersWithAccess(new HashSet<>(Collections.singletonList(createRouteDTO.getCreator())));
		}

		currentRoute.setUpdateDate(new Date());

		Route updatedRoute = routeRepository.save(currentRoute);

		RouteDTO updatedRouteDTO = dtoMapper.map(updatedRoute);
		updatedRouteDTO.setCreatorUserID(updatedRoute.getCreator().getUserId());

		return ResponseEntity.ok(updatedRouteDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RouteDTO> deleteRoute(@PathVariable String id) {
		routeRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
