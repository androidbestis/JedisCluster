package com.gupo.dao;

import com.gupo.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
	User selectByPrimaryKey(Integer id);
}
