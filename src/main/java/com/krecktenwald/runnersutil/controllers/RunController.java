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
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.RunDTO;
import com.krecktenwald.runnersutil.domain.entities.Run;
import com.krecktenwald.runnersutil.repositories.RunRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/runs")
public class RunController {

	private final RunRepository runRepository;

	@Autowired
	DTOMapper dtoMapper;

	public RunController(RunRepository runRepository) {
		this.runRepository = runRepository;
	}

	@GetMapping
	public List<Run> getRuns() {
		return runRepository.findAll();
	}

	@GetMapping("/{id}")
	public Run getRun(@PathVariable String id) {
		return runRepository.findById(id).orElseThrow(RuntimeException::new);
	}

	@PostMapping()
	public ResponseEntity<Run> createRun(@RequestBody @Valid RunDTO runDTO) throws URISyntaxException {
		Run run = dtoMapper.map(runDTO);
		run.setRunId(String.format("run_%s", UUID.randomUUID()));
		run.setCreateDate(new Date());
		Run savedRun = runRepository.save(run);
		return ResponseEntity.created(new URI("/runs/" + savedRun.getRunId())).body(savedRun);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Run> updateRun(@PathVariable String id, @RequestBody RunDTO runDTO) {
		Run currentRun = runRepository.findById(id).orElseThrow(RuntimeException::new);

		currentRun.setStartDateTime(runDTO.getStartDateTime());
		currentRun.setEndDateTime(runDTO.getEndDateTime());
		currentRun.setDistance(runDTO.getDistance());
		currentRun.setUpdateDate(new Date());

		currentRun = runRepository.save(dtoMapper.map(runDTO));

		return ResponseEntity.ok(currentRun);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Run> deleteRun(@PathVariable String id) {
		runRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
