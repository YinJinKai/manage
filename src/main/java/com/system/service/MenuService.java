package com.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.mapper.MenuMapper;

import st.core.mapper.BaseMapper;
import st.core.service.BaseService;
@Service("menuService")
public class MenuService<T> extends BaseService<T>{
	@Autowired
    private MenuMapper<T> menuMapper;
	@Override
	public MenuMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return menuMapper;
	}
	public List<T> selectAllmenu(T t){
		List<T> list = menuMapper.selectAllmenu(t);
		return list;
		
		
		
	}
}
