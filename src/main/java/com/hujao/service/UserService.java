package com.hujao.service;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hujao.common.JSON;
import com.hujao.model.UserModel;
import com.hujao.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	Producer emailProducer;
	
	public UserModel getById(String id){
		return userRepository.getById(id);
	}
	
	public UserModel add(UserModel model){
		model=userRepository.add(model);
		//按道理应该只发一个主题，由email和短息服务去分别执行
		Destination destination_topic = new ActiveMQTopic("registuser.topic");
		//本例中消费者都在内部，改用队列组合方式分发
		Destination destination_queue = new ActiveMQQueue("registuser4email.queue,registuser4sms.queue");
		// spring.jms.pub-sub-domain=false为队列模式 
		// 因为用了javaconfig重新定义,支持同时收发主题和队列
		emailProducer.sendMessage(destination_queue, JSON.toJson(model));		 
		return model;
	}
	
	public void comfirmEmail(String id){ 
		UserModel model=userRepository.getById(id);
		model.setEmailisvalid(true);
		userRepository.modify(model);
	}
	public void comfirmSms(String id){ 
		UserModel model=userRepository.getById(id);
		model.setMobileisvalid(true);
		userRepository.modify(model);
	}
}
