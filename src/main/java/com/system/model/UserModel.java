package com.system.model;

import st.core.model.BaseModel;

public class UserModel extends BaseModel{
private String userName;
private String password;
private String trueName;
private Integer roleCode;
private String classCode;
private String roleName;
private String className;
private String mysqlQueryCondition;
private Integer userCount;

public Integer getUserCount() {
	return userCount;
}
public void setUserCount(Integer userCount) {
	this.userCount = userCount;
}
public String getMysqlQueryCondition() {
	return mysqlQueryCondition;
}
public void setMysqlQueryCondition(String mysqlQueryCondition) {
	this.mysqlQueryCondition = mysqlQueryCondition;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public String getClassName() {
	return className;
}
public void setClassName(String className) {
	this.className = className;
}
public Integer getRoleCode() {
	return roleCode;
}
public void setRoleCode(Integer roleCode) {
	this.roleCode = roleCode;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getTrueName() {
	return trueName;
}
public void setTrueName(String trueName) {
	this.trueName = trueName;
}

public String getClassCode() {
	return classCode;
}
public void setClassCode(String classCode) {
	this.classCode = classCode;
}

}
