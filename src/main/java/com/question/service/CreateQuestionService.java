package com.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.question.mapper.CreateQuestionMapper;

import st.core.mapper.BaseMapper;
import st.core.service.BaseService;
@Service("createQuestionService")
public class CreateQuestionService<T> extends BaseService<T>{
	@Autowired
    private CreateQuestionMapper<T> createQuestionMapper;
	@Override
	public CreateQuestionMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return createQuestionMapper;
	}

}
