package com.krecktenwald.runnersutil.domain.dto.mapper.impl;

import com.krecktenwald.runnersutil.domain.dto.mapper.CRUDEntityVisitor;
import com.krecktenwald.runnersutil.domain.entities.AbstractCRUDEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRunDTO extends RunDTO{
	private String creatorUserId;
	private String routeId;

	@Override
	public <T extends AbstractCRUDEntity> T accept(CRUDEntityVisitor<T> crudEntityDTOVisitor) {
		return crudEntityDTOVisitor.visit(this);
	}
}
