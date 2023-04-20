package com.krecktenwald.runnersutil.domain.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class UserRecord extends AbstractCRUDEntity {

}
