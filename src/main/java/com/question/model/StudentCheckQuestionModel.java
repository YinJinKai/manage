package com.question.model;

import com.system.model.UserModel;

import st.core.model.BaseModel;

public class StudentCheckQuestionModel extends BaseModel{
	private String createQuestionCode;
	private String title;
	private String time;
	private String remark;
	private CreateQuestionModel createQuestionInfor;
	private QuestionQaModel  questionQaInfor;
	private UserModel  userInfor;
	
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
	public String getCreateQuestionCode() {
		return createQuestionCode;
	}
	public void setCreateQuestionCode(String createQuestionCode) {
		this.createQuestionCode = createQuestionCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
