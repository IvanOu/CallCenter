package com.ivan.cs;

import com.ivan.cs.intf.Employee;

/**
 * Created by ivanou on 2016/12/1.
 */
public class EmployeeImpl implements Employee {

	private String name;

	private RoleType role;

	private String ext;

	@Override
	public String getExt() {
		return ext;
	}

	@Override
	public RoleType getRole() {
		return role;
	}

	@Override
	public String getName() {
		return name;
	}
}
