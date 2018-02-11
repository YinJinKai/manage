package com.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.mapper.RolePermissionRelMapper;

import st.core.mapper.BaseMapper;
import st.core.service.BaseService;
@Service("rolePermissionRelService")
public class RolePermissionRelService<T> extends BaseService<T>{
	@Autowired
    private RolePermissionRelMapper<T> rolePermissionRelMapper;
	@Override
	public RolePermissionRelMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return rolePermissionRelMapper;
	}

}
