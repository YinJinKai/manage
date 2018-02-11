package com.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.question.mapper.StudentCheckQuestionMapper;

import st.core.mapper.BaseMapper;
import st.core.service.BaseService;
@Service("studentCheckQuestionService")
public class StudentCheckQuestionService<T> extends BaseService<T>{
	@Autowired
private StudentCheckQuestionMapper<T> studentCheckQuestionMapper;
	@Override
	public StudentCheckQuestionMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return studentCheckQuestionMapper;
	}

}
