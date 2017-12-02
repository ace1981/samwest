package com.hujao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hujao.model.UserModel;
import com.hujao.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public UserModel getById(String id){
		return userRepository.getById(id);
	}
	
	public UserModel add(UserModel model){
		return userRepository.add(model);
	}
}
