package com.krecktenwald.runnersutil.domain.entities;

import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.krecktenwald.runnersutil.domain.dto.mapper.CRUDEntityDTOVisitor;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.AbstractCRUDEntityDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

	@ManyToOne
	@JsonBackReference(value="user-created-routes")
	@JoinColumn(name = "route_creator")
	private User creator;

	@JsonManagedReference(value="runs-route")
	@OneToMany(mappedBy="route")
	private Set<Run> runs;

/*	@ManyToMany(mappedBy = "accessibleRoutes")
	private Set<User> usersWithAccess;*/

	@Override
	public <T extends AbstractCRUDEntityDTO> T accept(CRUDEntityDTOVisitor<T> crudEntityVisitor) {
		return crudEntityVisitor.visit(this);
	}
}
