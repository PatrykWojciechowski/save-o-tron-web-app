package com.wojciechowski.project.dao;

import com.wojciechowski.project.user.Role;

public interface RoleDAO {
	public Role findRoleByName(String theRoleName);
}
