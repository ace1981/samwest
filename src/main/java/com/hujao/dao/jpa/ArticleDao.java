package com.hujao.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hujao.po.ArticleEntity;

public interface ArticleDao extends JpaRepository<ArticleEntity, String> {
	 
}



 
