package com.hujao.repository;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.hujao.dao.jpa.ArticleDao;
import com.hujao.dao.mybatis.ArticleMapper;
import com.hujao.model.ArticleModel;
import com.hujao.po.ArticleEntity;

@Repository
@Transactional
public class ArticleRepository {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleMapper articleMapper;
    
    public ArticleModel add(ArticleModel model) {
    	ArticleEntity entity =articleDao.save(modelMapper.map(model,ArticleEntity.class));
    	return modelMapper.map(entity,ArticleModel.class);
    }
    
    public ArticleModel getById(String id) {    
    	 return modelMapper.map(articleMapper.getById(id),ArticleModel.class);
    }
}

 

    

   
