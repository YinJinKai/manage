package com.attendance.mapper;

import java.util.List;

import st.core.mapper.BaseMapper;


public interface LeaveMapper<T> extends BaseMapper<T>{
	List<T> selectByteacher(T t);
	List<T> selectByadmin(T t);
}
