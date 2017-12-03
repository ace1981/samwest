package com.hujao.service;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service("producer")
public class Producer {
	@Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
	private JmsMessagingTemplate jmsTemplate;

	// 发送消息，destination是发送到的队列，message是待发送的消息
	public void sendMessage(Destination destination, final String message) {
		jmsTemplate.convertAndSend(destination, message);
	}

	@JmsListener(destination = "sendemail.replyqueue")
	public void mailConsumerMessage(String text) {
		System.out.println("从sendemail.replyqueue队列收到的回复报文为:" + text);
	}
	@JmsListener(destination = "sendsms.replyqueue")
	public void smsConsumerMessage(String text) {
		System.out.println("从sendsms.replyqueue队列收到的回复报文为:" + text);
	}
}