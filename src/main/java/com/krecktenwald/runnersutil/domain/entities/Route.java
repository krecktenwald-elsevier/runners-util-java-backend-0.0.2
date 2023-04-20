package com.krecktenwald.runnersutil.domain.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
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
}
