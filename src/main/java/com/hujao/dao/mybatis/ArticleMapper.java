package com.hujao.dao.mybatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hujao.po.ArticleEntity;

public interface ArticleMapper { 
	@Select(value="select * from article where id = #{id}")
	ArticleEntity getById(@Param("id")String id);
}
