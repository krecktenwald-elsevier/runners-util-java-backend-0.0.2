package com.krecktenwald.runnersutil.domain.entities;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.krecktenwald.runnersutil.domain.dto.mapper.CRUDEntityDTOVisitor;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.AbstractCRUDEntityDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

	private String authId;

	private String emailAddress;

	@ManyToMany
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	@OneToMany(mappedBy = "runOwner", cascade = CascadeType.ALL)
	private Set<Run> runs = new HashSet<>();

	@OneToMany(mappedBy = "routeOwner", cascade = CascadeType.ALL)
	private Set<Route> routes = new HashSet<>();

	@Override
	public <T extends AbstractCRUDEntityDTO> T accept(CRUDEntityDTOVisitor<T> crudEntityVisitor) {
		return crudEntityVisitor.visit(this);
	}
}
