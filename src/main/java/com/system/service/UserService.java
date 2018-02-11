package com.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.mapper.UserMapper;
import com.system.model.UserModel;

import st.core.mapper.BaseMapper;
import st.core.service.BaseService;
@Service("userService")
public class UserService<T> extends BaseService<T>{
   @Autowired
   private UserMapper<T> userMapper;
	@Override
	public UserMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return userMapper;
	}
	
	public  List<T> selectAllst(T t){
		  List<T> list =userMapper.selectAllst(t);
		return list;
		  
		  
	  }
}
