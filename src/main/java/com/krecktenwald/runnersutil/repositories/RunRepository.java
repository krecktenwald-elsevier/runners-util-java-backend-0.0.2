package com.krecktenwald.runnersutil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krecktenwald.runnersutil.domain.entities.Run;

public interface RunRepository extends JpaRepository<Run, String> {
}
