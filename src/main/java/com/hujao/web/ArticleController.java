package com.hujao.web;

import java.lang.reflect.InvocationTargetException;

import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hujao.dto.ArticleDto;
import com.hujao.model.ArticleModel;
import com.hujao.service.ArticleService;

@Controller 
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ArticleService articleService;
	
	@RequestMapping("/{id}")
	public @ResponseBody  ArticleDto getById(@PathVariable("id")String id)
	{		 
		return modelMapper.map(articleService.getById(id), ArticleDto.class);		 
	}
	
	@RequestMapping(value="/new",method=RequestMethod.POST)
	public @ResponseBody ArticleDto addArticle(@RequestBody ArticleDto  dto)
	{	
		ArticleModel model=modelMapper.map(dto,ArticleModel.class);
		 
		return modelMapper.map(articleService.add(model), ArticleDto.class);		 
	}
}
