package com.system.model;

import st.core.model.BaseModel;

public class ClassModel extends BaseModel{
private String className;
private String classCode;
private String userName;
private String trueName;

public String getTrueName() {
	return trueName;
}
public void setTrueName(String trueName) {
	this.trueName = trueName;
}
public String getClassName() {
	return className;
}
public void setClassName(String className) {
	this.className = className;
}
public String getClassCode() {
	return classCode;
}
public void setClassCode(String classCode) {
	this.classCode = classCode;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}

}
