package com.hujao.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hujao.po.UserEntity;

public interface UserMapper { 
	@Select(value="select * from User where id = #{id}")
	UserEntity getById(@Param("id")String id);
	
	@Select(value="select * from User")
	List<UserEntity> getAll();
}
