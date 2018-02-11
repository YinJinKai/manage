package com.system.mapper;

import java.util.List;

import st.core.mapper.BaseMapper;

public interface UserMapper<T> extends BaseMapper<T>{
  List<T> selectAllst(T t);
}
