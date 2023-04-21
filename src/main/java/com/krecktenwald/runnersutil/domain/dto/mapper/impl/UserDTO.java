package com.krecktenwald.runnersutil.domain.dto.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.krecktenwald.runnersutil.domain.dto.mapper.CRUDEntityVisitor;
import com.krecktenwald.runnersutil.domain.entities.AbstractCRUDEntity;
import com.krecktenwald.runnersutil.domain.entities.Route;
import com.krecktenwald.runnersutil.domain.entities.Run;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends AbstractCRUDEntityDTO {
	private String userId;

	private Set<Run> runs;

	private Set<Route> createdRoutes;

	/*private Set<Route> accessibleRoutes;*/

	@Override
	public <T extends AbstractCRUDEntity> T accept(CRUDEntityVisitor<T> crudEntityDTOVisitor) {
		return crudEntityDTOVisitor.visit(this);
	}
}
