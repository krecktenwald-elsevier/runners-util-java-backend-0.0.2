package com.krecktenwald.runnersutil.domain.dto.mapper;

import com.krecktenwald.runnersutil.domain.entities.Route;
import com.krecktenwald.runnersutil.domain.entities.Run;
import com.krecktenwald.runnersutil.domain.entities.User;

public interface CRUDEntityDTOVisitor<T> {
	T visit(Route route);

	T visit(User user);

	T visit(Run run);
}
