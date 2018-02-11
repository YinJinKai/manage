package com.attendance.model;

import com.system.model.ClassModel;
import com.system.model.UserModel;

import st.core.model.BaseModel;

public class WorkModel extends BaseModel{
private String userName;
private String oneCheck;
private String twoCheck;
private String threeCheck;
private String fourCheck;
private String fiveCheck;
private String sixCheck;
private String time;
private Integer score;
private ClassModel classInfor;//做映射的类
private UserModel userInfor;//做映射的类
private String startTime;
private String endTime;
private String trueName;
private String qingjia;

public String getQingjia() {
	return qingjia;
}
public void setQingjia(String qingjia) {
	this.qingjia = qingjia;
}
public String getTrueName() {
	return trueName;
}
public void setTrueName(String trueName) {
	this.trueName = trueName;
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
public ClassModel getClassInfor() {
	return classInfor;
}
public void setClassInfor(ClassModel classInfor) {
	this.classInfor = classInfor;
}
public UserModel getUserInfor() {
	return userInfor;
}
public void setUserInfor(UserModel userInfor) {
	this.userInfor = userInfor;
}
public Integer getScore() {
	return score;
}
public void setScore(Integer score) {
	this.score = score;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getOneCheck() {
	return oneCheck;
}
public void setOneCheck(String oneCheck) {
	this.oneCheck = oneCheck;
}
public String getTwoCheck() {
	return twoCheck;
}
public void setTwoCheck(String twoCheck) {
	this.twoCheck = twoCheck;
}
public String getThreeCheck() {
	return threeCheck;
}
public void setThreeCheck(String threeCheck) {
	this.threeCheck = threeCheck;
}
public String getFourCheck() {
	return fourCheck;
}
public void setFourCheck(String fourCheck) {
	this.fourCheck = fourCheck;
}
public String getFiveCheck() {
	return fiveCheck;
}
public void setFiveCheck(String fiveCheck) {
	this.fiveCheck = fiveCheck;
}
public String getSixCheck() {
	return sixCheck;
}
public void setSixCheck(String sixCheck) {
	this.sixCheck = sixCheck;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}

}
