package com.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.question.mapper.QuestionQaMapper;

import st.core.mapper.BaseMapper;
import st.core.service.BaseService;
@Service("questionQaService")
public class QuestionQaService<T> extends BaseService<T>{
	@Autowired
    private QuestionQaMapper<T> questionMapper;
	@Override
	public QuestionQaMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return questionMapper;
	}

}
