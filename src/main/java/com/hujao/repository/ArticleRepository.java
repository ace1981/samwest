package com.hujao.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.hujao.dao.jpa.ArticleDao;
import com.hujao.dao.mybatis.ArticleMapper;
import com.hujao.dao.search.ArticleSearchDao;
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
	private ArticleSearchDao articleSearchDao;

    @Autowired
    private ArticleMapper articleMapper;
    
    public ArticleModel add(ArticleModel model) {
    	ArticleEntity entity =articleDao.save(modelMapper.map(model,ArticleEntity.class));
    	articleSearchDao.save(model);
    	return modelMapper.map(entity,ArticleModel.class);
    }
    
    public ArticleModel getById(String id) {    
    	 return modelMapper.map(articleMapper.getById(id),ArticleModel.class);
    }
    
    public List<ArticleModel> searchByTitle(String queryString) {    
    	 //String queryString = "springboot";//搜索关键字
         QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
         Iterable<ArticleModel> searchResult = articleSearchDao.search(builder);
         Iterator<ArticleModel> iterator = searchResult.iterator();
         List<ArticleModel>list=new ArrayList();
         while (iterator.hasNext()) {       	 
        	 list.add(modelMapper.map(iterator.next(), ArticleModel.class));
             System.out.println(iterator.next());
         }
         return list;
   }
    
}

 

    

   
