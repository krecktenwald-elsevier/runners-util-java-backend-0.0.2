package com.krecktenwald.runnersutil.domain.entities;

import java.util.Date;

import com.krecktenwald.runnersutil.domain.dto.mapper.VisitableCRUDEntity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractCRUDEntity implements VisitableCRUDEntity {
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;
}
