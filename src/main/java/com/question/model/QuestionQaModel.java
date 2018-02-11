package com.question.model;

import st.core.model.BaseModel;

public class QuestionQaModel extends BaseModel{
private String questionQaCode;
private String tm;
private String a;
private String b;
private String c;
private String d;
private String daan;
private String tmtype;

public String getTmtype() {
	return tmtype;
}
public void setTmtype(String tmtype) {
	this.tmtype = tmtype;
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
public String getQuestionQaCode() {
	return questionQaCode;
}
public void setQuestionQaCode(String questionQaCode) {
	this.questionQaCode = questionQaCode;
}
public String getTm() {
	return tm;
}
public void setTm(String tm) {
	this.tm = tm;
}

public String getDaan() {
	return daan;
}
public void setDaan(String daan) {
	this.daan = daan;
}

}
