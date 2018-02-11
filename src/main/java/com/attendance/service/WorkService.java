package com.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.mapper.WorkMapper;

import st.core.mapper.BaseMapper;
import st.core.service.BaseService;
@Service("workService")
public class WorkService<T> extends BaseService<T>{
	@Autowired
    private WorkMapper<T> workMapper;
	@Override
	public WorkMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return workMapper;
	}

}
