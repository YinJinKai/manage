package com.question.model;

import java.util.Arrays;

import st.core.model.BaseModel;

public class CreateQuestionModel extends BaseModel{
private String questionName;
private String createQuestionCode;
private String userName;
public String getQuestionName() {
	return questionName;
}
public void setQuestionName(String questionName) {
	this.questionName = questionName;
}
public String getCreateQuestionCode() {
	return createQuestionCode;
}
public void setCreateQuestionCode(String createQuestionCode) {
	this.createQuestionCode = createQuestionCode;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}

}
