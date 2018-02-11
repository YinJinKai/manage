package com.question.model;

import com.system.model.UserModel;

import st.core.model.BaseModel;

public class StudentCheckWitchModel extends BaseModel{
private String userName;
private String createQuestionCode;
private Integer titleid;
private String level;
private String questionQaCodelist;
private String daNamelist;
private CreateQuestionModel createQuestionInfor;
private QuestionQaModel  questionQaInfor=new QuestionQaModel();
private UserModel  userInfor;
private StudentCheckQuestionModel  studentCheckInfor;
private StudentAnswerModel  answerInfor;
private String trueName;
private String questionName;
private String tm;
private String a;
private String b;
private String c;
private String d;
private String daan;
private String tmtype;
private String title;
private String daName;

public String getTrueName() {
	return trueName;
}
public void setTrueName(String trueName) {
	this.trueName = trueName;
}
public String getQuestionName() {
	return questionName;
}
public void setQuestionName(String questionName) {
	this.questionName = questionName;
}
public String getTm() {
	return tm;
}
public void setTm(String tm) {
	this.tm = tm;
}
public String getA() {
	return a;
}
public void setA(String a) {
	this.a = a;
}
public String getB() {
	return b;
}
public void setB(String b) {
	this.b = b;
}
public String getC() {
	return c;
}
public void setC(String c) {
	this.c = c;
}
public String getD() {
	return d;
}
public void setD(String d) {
	this.d = d;
}
public String getDaan() {
	return daan;
}
public void setDaan(String daan) {
	this.daan = daan;
}
public String getTmtype() {
	return tmtype;
}
public void setTmtype(String tmtype) {
	this.tmtype = tmtype;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDaName() {
	return daName;
}
public void setDaName(String daName) {
	this.daName = daName;
}
public StudentCheckQuestionModel getStudentCheckInfor() {
	return studentCheckInfor;
}
public void setStudentCheckInfor(StudentCheckQuestionModel studentCheckInfor) {
	this.studentCheckInfor = studentCheckInfor;
}
public StudentAnswerModel getAnswerInfor() {
	return answerInfor;
}
public void setAnswerInfor(StudentAnswerModel answerInfor) {
	this.answerInfor = answerInfor;
}
public CreateQuestionModel getCreateQuestionInfor() {
	return createQuestionInfor;
}
public void setCreateQuestionInfor(CreateQuestionModel createQuestionInfor) {
	this.createQuestionInfor = createQuestionInfor;
}
public QuestionQaModel getQuestionQaInfor() {
	return questionQaInfor;
}
public void setQuestionQaInfor(QuestionQaModel questionQaInfor) {
	this.questionQaInfor = questionQaInfor;
}
public UserModel getUserInfor() {
	return userInfor;
}
public void setUserInfor(UserModel userInfor) {
	this.userInfor = userInfor;
}
public String getQuestionQaCodelist() {
	return questionQaCodelist;
}
public void setQuestionQaCodelist(String questionQaCodelist) {
	this.questionQaCodelist = questionQaCodelist;
}
public String getDaNamelist() {
	return daNamelist;
}
public void setDaNamelist(String daNamelist) {
	this.daNamelist = daNamelist;
}
public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getCreateQuestionCode() {
	return createQuestionCode;
}
public void setCreateQuestionCode(String createQuestionCode) {
	this.createQuestionCode = createQuestionCode;
}
public Integer getTitleid() {
	return titleid;
}
public void setTitleid(Integer titleid) {
	this.titleid = titleid;
}

}
