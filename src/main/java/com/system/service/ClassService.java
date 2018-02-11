package com.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.system.mapper.ClassMapper;

import st.core.mapper.BaseMapper;
import st.core.service.BaseService;
@Service("classService")
public class ClassService<T> extends BaseService<T>{
	@Autowired
   private ClassMapper<T> classMapper;
	@Override
	public ClassMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return classMapper;
	}

}
