package com.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.question.mapper.StudentAnswerMapper;

import st.core.mapper.BaseMapper;
import st.core.service.BaseService;
@Service("studentAnswerService")
public class StudentAnswerService<T> extends BaseService<T>{
	@Autowired
    private StudentAnswerMapper<T> studentAnswerMapper;
	@Override
	public StudentAnswerMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return studentAnswerMapper;
	}

}
