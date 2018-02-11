package com.system.model;

import st.core.model.BaseModel;

public class RolePermissionRelModel extends BaseModel{
private String menuCode;
private Integer roleCode;
public String getMenuCode() {
	return menuCode;
}
public void setMenuCode(String menuCode) {
	this.menuCode = menuCode;
}
public Integer getRoleCode() {
	return roleCode;
}
public void setRoleCode(Integer roleCode) {
	this.roleCode = roleCode;
}

}
