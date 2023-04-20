package com.krecktenwald.runnersutil.domain.dto.mapper;

import com.krecktenwald.runnersutil.domain.dto.mapper.impl.RouteDTO;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.RunDTO;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.UserDTO;

public interface CRUDEntityVisitor<T> {
	T visit(UserDTO userDTO);

	T visit(RunDTO runDTO);

	T visit(RouteDTO routeDTO);
}
