package com.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.mapper.DataMapper;

import st.core.mapper.BaseMapper;
import st.core.service.BaseService;
@Service("dataService")
public class DataService<T> extends BaseService<T>{
	@Autowired
   private DataMapper<T> dataMapper;
	@Override
	public DataMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return dataMapper;
	}

}
