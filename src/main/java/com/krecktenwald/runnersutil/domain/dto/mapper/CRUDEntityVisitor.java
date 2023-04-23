package com.krecktenwald.runnersutil.domain.dto.mapper;

import com.krecktenwald.runnersutil.domain.dto.mapper.impl.CreateRouteDTO;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.CreateRunDTO;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.RouteDTO;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.RunDTO;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.UserDTO;
import com.krecktenwald.runnersutil.domain.entities.Route;

public interface CRUDEntityVisitor<T> {
	T visit(UserDTO userDTO);

	T visit(RunDTO runDTO);

	T visit(RouteDTO routeDTO);

	T visit(CreateRouteDTO routeDTO);

	T visit(CreateRunDTO routeDTO);
}
