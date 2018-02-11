package com.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.question.mapper.QuestionNameQaRelMapper;

import st.core.service.BaseService;

@Service("questionNameQaRelService")
public class QuestionNameQaRelService<T> extends BaseService<T>{
	@Autowired
    private QuestionNameQaRelMapper<T> questionNameQaRelMapper;
	@Override
	public QuestionNameQaRelMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return questionNameQaRelMapper;
	}

}
