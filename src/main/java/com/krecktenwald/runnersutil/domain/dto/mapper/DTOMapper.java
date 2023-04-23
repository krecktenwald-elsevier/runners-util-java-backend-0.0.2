package com.krecktenwald.runnersutil.domain.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.krecktenwald.runnersutil.domain.dto.mapper.impl.AbstractCRUDEntityDTO;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.CreateRouteDTO;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.CreateRunDTO;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.RouteDTO;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.RunDTO;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.UserDTO;
import com.krecktenwald.runnersutil.domain.entities.AbstractCRUDEntity;
import com.krecktenwald.runnersutil.domain.entities.Route;
import com.krecktenwald.runnersutil.domain.entities.Run;
import com.krecktenwald.runnersutil.domain.entities.User;

/**
 * * A MapStruct (mapstruct.org) based mapping interface for converting
 * POJO/Entity objects to Data Transfer Objects (DTOs), and vice-versa
 */
@Mapper(componentModel = "spring")
public abstract class DTOMapper implements CRUDEntityVisitor<AbstractCRUDEntity>, CRUDEntityDTOVisitor<AbstractCRUDEntityDTO> {
	public AbstractCRUDEntity crudEntityDTOToCrudEntity(AbstractCRUDEntityDTO crudEntityDTO) {
		return crudEntityDTO.accept(this);
	}

	@Override
	public User visit(UserDTO userDTO) {
		return map(userDTO);
	}

	@Override
	public UserDTO visit(User user) {
		return map(user);
	}

	@Override
	public Run visit(RunDTO runDTO) {
		return map(runDTO);
	}

	@Override
	public RunDTO visit(Run run) {
		return map(run);
	}

	@Override
	public Run visit(CreateRunDTO createRunDTO) {
		return map(createRunDTO);
	}

	@Override
	public Route visit(RouteDTO routeDTO) {
		return map(routeDTO);
	}

	@Override
	public RouteDTO visit(Route route) {
		return map(route);
	}

	@Override
	public Route visit(CreateRouteDTO createRouteDTO) {
		return map(createRouteDTO);
	}

	@Mapping(target = "runs", ignore = true)
	@Mapping(target = "createdRoutes", ignore = true)
	public abstract User map(UserDTO value);

	@Mapping(target = "runIDs", ignore = true)
	@Mapping(target = "createdRouteIDs", ignore = true)
	public abstract UserDTO map(User value);

	@Mapping(target = "user", ignore = true)
	@Mapping(target = "route", ignore = true)
	public abstract Run map(RunDTO value);

	@Mapping(target = "user", ignore = true)
	@Mapping(target = "route", ignore = true)
	public abstract Run map(CreateRunDTO value);

	@Mapping(target = "userId", ignore = true)
	@Mapping(target = "routeId", ignore = true)
	public abstract RunDTO map(Run value);

	@Mapping(target = "runs", ignore = true)
	@Mapping(target = "creator", ignore = true)
	public abstract Route map(RouteDTO value);

	@Mapping(target = "creatorUserID", ignore = true)
	public abstract RouteDTO map(Route value);

	@Mapping(target = "runs", ignore = true)
	@Mapping(target = "creator", ignore = true)
	public abstract Route map(CreateRouteDTO value);
}
