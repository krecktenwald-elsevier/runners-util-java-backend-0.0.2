package com.krecktenwald.runnersutil.domain.entities;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.krecktenwald.runnersutil.domain.dto.mapper.CRUDEntityDTOVisitor;
import com.krecktenwald.runnersutil.domain.dto.mapper.impl.AbstractCRUDEntityDTO;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "runs")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Run extends AbstractCRUDEntity {
	@Id
	@GenericGenerator(name = "run_id", strategy = "uuid2")
	private String runId;

	@Column(name = "distance")
	private Integer distance;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date_time")
	private Date startDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date_time")
	private Date endDateTime;

	@OneToOne
	@JoinColumn(name = "route_id")
	private Route route;

	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	@Override
	public <T extends AbstractCRUDEntityDTO> T accept(CRUDEntityDTOVisitor<T> crudEntityVisitor) {
		return crudEntityVisitor.visit(this);
	}
}
