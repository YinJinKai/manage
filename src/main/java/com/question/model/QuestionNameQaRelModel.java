package com.question.model;

import com.system.model.UserModel;

import st.core.model.BaseModel;

public class QuestionNameQaRelModel extends BaseModel{
	private String createQuestionCode;
	private String questionQaCode;
	private String codelist;
	private CreateQuestionModel createQuestionInfor;
	private QuestionQaModel  questionQaInfor;
	private UserModel  userInfor;
	private String idlist;//要修改的id和值
	private String orderBylist;//相对的id和值
    private String orderByName;
    private String reorderidlist;
    private String userName;
    private Integer titleid;
   
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getTitleid() {
		return titleid;
	}
	public void setTitleid(Integer titleid) {
		this.titleid = titleid;
	}
	public String getReorderidlist() {
		return reorderidlist;
	}
	public void setReorderidlist(String reorderidlist) {
		this.reorderidlist = reorderidlist;
	}
	public String getOrderBylist() {
		return orderBylist;
	}
	public void setOrderBylist(String orderBylist) {
		this.orderBylist = orderBylist;
	}
	public String getOrderByName() {
		return orderByName;
	}
	public void setOrderByName(String orderByName) {
		this.orderByName = orderByName;
	}
	public String getIdlist() {
		return idlist;
	}
	public void setIdlist(String idlist) {
		this.idlist = idlist;
	}
	public UserModel getUserInfor() {
		return userInfor;
	}
	public void setUserInfor(UserModel userInfor) {
		this.userInfor = userInfor;
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
	public String getCodelist() {
		return codelist;
	}
	public void setCodelist(String codelist) {
		this.codelist = codelist;
	}
	public String getCreateQuestionCode() {
		return createQuestionCode;
	}
	public void setCreateQuestionCode(String createQuestionCode) {
		this.createQuestionCode = createQuestionCode;
	}
	public String getQuestionQaCode() {
		return questionQaCode;
	}
	public void setQuestionQaCode(String questionQaCode) {
		this.questionQaCode = questionQaCode;
	}
	
}
