package com.question.model;

import st.core.model.BaseModel;

public class StudentAnswerModel extends BaseModel{
private String daName;
private Integer witchId;
private String questionQaCode;
public String getDaName() {
	return daName;
}
public void setDaName(String daName) {
	this.daName = daName;
}
public Integer getWitchId() {
	return witchId;
}
public void setWitchId(Integer witchId) {
	this.witchId = witchId;
}
public String getQuestionQaCode() {
	return questionQaCode;
}
public void setQuestionQaCode(String questionQaCode) {
	this.questionQaCode = questionQaCode;
}

}
