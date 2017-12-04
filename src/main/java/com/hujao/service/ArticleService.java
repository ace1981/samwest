package com.hujao.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hujao.common.JSON;
import com.hujao.common.JedisManager;
import com.hujao.model.ArticleModel;
import com.hujao.model.UserModel;
import com.hujao.repository.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	ArticleRepository articleRepository;

	public ArticleModel getById(String id) {
		String json=JedisManager.getInstance().Get("ArticleModel" + id);
		if (json!= null) {
			// 取缓存
			return JSON.toBean(json, ArticleModel.class);
		}
		return articleRepository.getById(id);
	}

	public ArticleModel add(ArticleModel model) {
		model = articleRepository.add(model);
		// 缓存
		JedisManager.getInstance().Set("ArticleModel" + model.getId(), JSON.toJson(model));
		return model;
	}

}
