package com.system.model;

import st.core.model.BaseModel;

public class MenuModel extends BaseModel{
	private String PermissionName;
	private String url;
	private String pcCode;
	private String menuCode;
	private Integer roleCode;
	
	public Integer getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(Integer roleCode) {
		this.roleCode = roleCode;
	}
	public String getPermissionName() {
		return PermissionName;
	}
	public void setPermissionName(String permissionName) {
		PermissionName = permissionName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPcCode() {
		return pcCode;
	}
	public void setPcCode(String pcCode) {
		this.pcCode = pcCode;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
}
