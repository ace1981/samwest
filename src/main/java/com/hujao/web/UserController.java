package com.hujao.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.hujao.dto.UserDto;
 
import com.hujao.model.UserModel;
import com.hujao.service.ArticleService;
import com.hujao.service.UserService;

@Controller 
@RequestMapping("/user")
public class UserController {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/new",method=RequestMethod.POST)
	public @ResponseBody UserDto addUser(@RequestBody UserDto  dto)
	{	
		UserModel model=modelMapper.map(dto,UserModel.class);
		 
		return modelMapper.map(userService.add(model), UserDto.class);		 
	}
}
