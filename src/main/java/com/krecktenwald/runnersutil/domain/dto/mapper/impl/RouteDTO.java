package com.krecktenwald.runnersutil.domain.dto.mapper.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.krecktenwald.runnersutil.domain.dto.mapper.CRUDEntityVisitor;
import com.krecktenwald.runnersutil.domain.entities.AbstractCRUDEntity;
import com.krecktenwald.runnersutil.domain.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDTO extends AbstractCRUDEntityDTO {
	private String routeId;

	private String name;

	private Integer distance;

	private User creator;

/*
	private Set<User> usersWithAccess = new HashSet<>();
*/

	@Override
	public <T extends AbstractCRUDEntity> T accept(CRUDEntityVisitor<T> crudEntityDTOVisitor) {
		return crudEntityDTOVisitor.visit(this);
	}
}
