package com.krecktenwald.runnersutil.domain.entities;

import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.krecktenwald.runnersutil.domain.dto.mapper.CRUDEntityDTOVisitor;
import com.krecktenwald.runnersutil.domain.dto.mapper.CRUDEntityVisitor;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.AbstractCRUDEntityDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "routes")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Route extends AbstractCRUDEntity {
	@Id
	@GenericGenerator(name = "route_id", strategy = "uuid2")
	private String routeId;

	@Column(name = "name")
	private String name;

	@Column(name = "distance")
	private Integer distance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties("createdRoutes")
	@JoinColumn(name = "creator_id")
	private User creator;

/*	@ManyToMany(mappedBy = "accessibleRoutes")
	private Set<User> usersWithAccess;*/

	@Override
	public <T extends AbstractCRUDEntityDTO> T accept(CRUDEntityDTOVisitor<T> crudEntityVisitor) {
		return crudEntityVisitor.visit(this);
	}
}
