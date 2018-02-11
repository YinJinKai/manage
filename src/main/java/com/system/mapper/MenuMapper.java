package com.system.mapper;

import java.util.List;

import st.core.mapper.BaseMapper;

public interface MenuMapper<T> extends BaseMapper<T>{
	List<T> selectAllmenu(T t);
}
