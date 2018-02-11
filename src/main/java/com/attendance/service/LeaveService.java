package com.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.mapper.LeaveMapper;

import st.core.mapper.BaseMapper;
import st.core.service.BaseService;
@Service("leaveService")
public class LeaveService<T> extends BaseService<T>{
   @Autowired
   private LeaveMapper<T> leaveMapper;
	@Override
	public LeaveMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return leaveMapper;
	}
   public List<T> selectByteacher(T t){
	   List<T> list = leaveMapper.selectByteacher(t);
	return list;
	   
	   
   }
	public List<T> selectByadmin(T t){
		List<T> list = leaveMapper.selectByadmin(t);
		return list;
		
		
	};
}
