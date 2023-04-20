package com.krecktenwald.runnersutil.domain.dto.mapper;

import com.krecktenwald.runnersutil.domain.entities.AbstractCRUDEntity;

public interface VisitableCRUDEntityDTO {
	<T extends AbstractCRUDEntity> T accept(CRUDEntityVisitor<T> crudEntityDTOVisitor);
}
