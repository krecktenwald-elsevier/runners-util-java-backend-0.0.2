package com.krecktenwald.runnersutil.domain.dto.mapper.impl;

import java.util.Set;

import com.krecktenwald.runnersutil.domain.entities.Permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {
	private String roleId;
	private String name;
	private String description;
	private Set<String> userIds; // Instead of full User objects
	private Set<Permission> permissions;
}
