package com.krecktenwald.runnersutil.domain.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.krecktenwald.runnersutil.domain.dto.mapper.CRUDEntityDTOVisitor;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.AbstractCRUDEntityDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
public class User extends AbstractCRUDEntity{
	@Id
	@GenericGenerator(name = "user_id", strategy = "uuid2")
	private String userId;

	@OneToMany(mappedBy="user")
	private Set<Run> runs;

	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Route> createdRoutes;

	/*@ManyToMany
	@JoinTable(
			name = "users_accessible_routes",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "route_id")
	)
	private Set<Route> accessibleRoutes;*/

	@Override
	public <T extends AbstractCRUDEntityDTO> T accept(CRUDEntityDTOVisitor<T> crudEntityVisitor) {
		return crudEntityVisitor.visit(this);
	}
}
