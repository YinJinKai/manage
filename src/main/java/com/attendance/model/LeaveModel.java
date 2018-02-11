package com.attendance.model;

import com.system.model.ClassModel;
import com.system.model.UserModel;

import st.core.model.BaseModel;

public class LeaveModel extends BaseModel{
private String trueName;
private String userName;
private String why;
private String leaveTime;
private String startTime;
private String endTime;
private String status;
private String statusName;
private UserModel userInfor;//做映射的类
private ClassModel classInfor;//做映射的类
private String teacherName;
private String idlist;
private String classCode;


public String getClassCode() {
	return classCode;
}
public void setClassCode(String classCode) {
	this.classCode = classCode;
}
public String getStatusName() {
	return statusName;
}
public void setStatusName(String statusName) {
	this.statusName = statusName;
}
public String getIdlist() {
	return idlist;
}
public void setIdlist(String idlist) {
	this.idlist = idlist;
}
public String getTrueName() {
	return trueName;
}
public void setTrueName(String trueName) {
	this.trueName = trueName;
}
public ClassModel getClassInfor() {
	return classInfor;
}
public void setClassInfor(ClassModel classInfor) {
	this.classInfor = classInfor;
}
public String getTeacherName() {
	return teacherName;
}
public void setTeacherName(String teacherName) {
	this.teacherName = teacherName;
}
public UserModel getUserInfor() {
	return userInfor;
}
public void setUserInfor(UserModel userInfor) {
	this.userInfor = userInfor;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getWhy() {
	return why;
}
public void setWhy(String why) {
	this.why = why;
}
public String getLeaveTime() {
	return leaveTime;
}
public void setLeaveTime(String leaveTime) {
	this.leaveTime = leaveTime;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}
