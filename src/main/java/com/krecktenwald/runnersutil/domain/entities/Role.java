package com.krecktenwald.runnersutil.domain.entities;

import java.security.Permission;
import java.util.Set;

import com.krecktenwald.runnersutil.domain.dto.mapper.CRUDEntityDTOVisitor;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.AbstractCRUDEntityDTO;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends AbstractCRUDEntity {
	@Id
	private String roleId;

	private String name;
	private String description;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	@ManyToMany
	private Set<Permission> permissions;

	@Override
	public <T extends AbstractCRUDEntityDTO> T accept(CRUDEntityDTOVisitor<T> crudEntityVisitor) {
		return crudEntityVisitor.visit(this);
	}
}
