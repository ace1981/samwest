package com.hujao.repository;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.hujao.dao.jpa.UserDao;
import com.hujao.dao.mybatis.UserMapper;
import com.hujao.model.UserModel;
import com.hujao.po.UserEntity;

@Repository
@Transactional
public class UserRepository {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;
    
    public UserModel add(UserModel model) {
    	UserEntity entity =userDao.save(modelMapper.map(model,UserEntity.class));
    	return modelMapper.map(entity,UserModel.class);
    }
    
    public UserModel modify(UserModel model) {
    	UserEntity entity =userDao.save(modelMapper.map(model,UserEntity.class));
    	return modelMapper.map(entity,UserModel.class);
    }
    
    public UserModel getById(String id) {    
    	 return modelMapper.map(userMapper.getById(id),UserModel.class);
    }
}

 

    

   
