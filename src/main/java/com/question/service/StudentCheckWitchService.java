package com.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.question.mapper.StudentCheckQuestionMapper;
import com.question.mapper.StudentCheckWitchMapper;

import st.core.mapper.BaseMapper;
import st.core.service.BaseService;
@Service("studentCheckWitchService")
public class StudentCheckWitchService<T> extends BaseService<T>{
	@Autowired
private StudentCheckWitchMapper<T> studentCheckWitchMapper;
	@Override
	public StudentCheckWitchMapper<T>  getMapper() {
		// TODO Auto-generated method stub
		return studentCheckWitchMapper;
	}

}
