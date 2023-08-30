package com.krecktenwald.runnersutil.domain.dto.mapper;

import com.krecktenwald.runnersutil.domain.entities.Permission;
import com.krecktenwald.runnersutil.domain.entities.Role;
import com.krecktenwald.runnersutil.domain.entities.Route;
import com.krecktenwald.runnersutil.domain.entities.Run;
import com.krecktenwald.runnersutil.domain.entities.User;

public interface CRUDEntityDTOVisitor<T> {
	T visit(Route route);

	T visit(User user);

	T visit(Role role);

	T visit(Permission permission);

	T visit(Run run);
}
