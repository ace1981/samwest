package com.hujao.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hujao.dto.UserDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller 
@RequestMapping("/mem")
public class MemTestController {
	//static 
	@RequestMapping(value="/new",method=RequestMethod.GET)
	//@ApiOperation(value="用户注册接口", notes="用户注册详细描述")
	public @ResponseBody void addUser()
	{	
		String[] aaa = new String[2000000];
	}
}
