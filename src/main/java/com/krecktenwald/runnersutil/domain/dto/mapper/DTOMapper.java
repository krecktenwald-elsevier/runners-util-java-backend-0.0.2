package com.krecktenwald.runnersutil.domain.dto.mapper;

import org.mapstruct.Mapper;

import com.krecktenwald.runnersutil.domain.dto.mapper.impl.AbstractCRUDEntityDTO;
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
public abstract class DTOMapper implements CRUDEntityVisitor<AbstractCRUDEntity> {
	public AbstractCRUDEntity crudEntityDTOToCrudEntity(AbstractCRUDEntityDTO crudEntityDTO) {
		return crudEntityDTO.accept(this);
	}

	@Override
	public User visit(UserDTO userDTO) {
		return map(userDTO);
	}

	@Override
	public Run visit(RunDTO runDTO) {
		return map(runDTO);
	}

	@Override
	public Route visit(RouteDTO routeDTO) {
		return map(routeDTO);
	}

	public abstract User map(UserDTO value);

	public abstract Run map(RunDTO value);

	public abstract Route map(RouteDTO value);
}
