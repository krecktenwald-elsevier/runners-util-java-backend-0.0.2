package com.krecktenwald.runnersutil.domain.dto.mapper.impl;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.krecktenwald.runnersutil.domain.dto.mapper.VisitableCRUDEntityDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractCRUDEntityDTO implements VisitableCRUDEntityDTO {
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private Date createDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private Date updateDate;
}
