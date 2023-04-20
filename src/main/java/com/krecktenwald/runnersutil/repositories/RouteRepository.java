package com.krecktenwald.runnersutil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krecktenwald.runnersutil.domain.entities.Route;

public interface RouteRepository extends JpaRepository<Route, String> {
}
