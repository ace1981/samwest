package com.hujao.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hujao.model.ArticleModel;
import com.hujao.repository.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	ArticleRepository articleRepository;
	
	
	public ArticleModel getById(String id){
		return articleRepository.getById(id);
	}
	
	public ArticleModel add(ArticleModel model){
		return articleRepository.add(model);
	}
	
}
