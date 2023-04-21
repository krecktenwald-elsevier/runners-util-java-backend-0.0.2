package com.krecktenwald.runnersutil.domain.dto.mapper.impl;

import com.krecktenwald.runnersutil.domain.dto.mapper.CRUDEntityVisitor;
import com.krecktenwald.runnersutil.domain.entities.AbstractCRUDEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRouteDTO extends RouteDTO{
	private String creatorUserId;

	@Override
	public <T extends AbstractCRUDEntity> T accept(CRUDEntityVisitor<T> crudEntityDTOVisitor) {
		return crudEntityDTOVisitor.visit(this);
	}
}
