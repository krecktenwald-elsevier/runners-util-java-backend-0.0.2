package com.krecktenwald.runnersutil.domain.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
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
}
