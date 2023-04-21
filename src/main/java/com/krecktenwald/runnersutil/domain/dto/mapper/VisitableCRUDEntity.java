package com.krecktenwald.runnersutil.domain.dto.mapper;

import com.krecktenwald.runnersutil.domain.dto.mapper.impl.AbstractCRUDEntityDTO;

public interface VisitableCRUDEntity {
	<T extends AbstractCRUDEntityDTO> T accept(CRUDEntityDTOVisitor<T> crudEntityVisitor);
}
