package com.krecktenwald.runnersutil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krecktenwald.runnersutil.domain.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
}
