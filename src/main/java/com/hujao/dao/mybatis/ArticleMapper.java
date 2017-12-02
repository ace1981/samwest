package com.hujao.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hujao.po.ArticleEntity;

public interface ArticleMapper { 
	@Select(value="select * from article where id = #{id}")
	ArticleEntity getById(@Param("id")String id);
	
	@Select(value="select * from article")
	List<ArticleEntity> getAll();
}
