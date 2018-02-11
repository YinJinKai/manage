package com.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.mapper.RoleMapper;

import st.core.mapper.BaseMapper;
import st.core.service.BaseService;
@Service("roleService")
public class RoleService<T> extends BaseService<T>{
	@Autowired
    private RoleMapper<T> roleMapper;
	@Override
	public RoleMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return roleMapper;
	}

}
