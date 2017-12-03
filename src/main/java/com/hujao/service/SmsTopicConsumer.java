package com.hujao.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SmsTopicConsumer {
	// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
	@JmsListener(destination = "registuser.topic")
	public void receiveTopic(String text) {
		System.out.println("SmsTopicConsumer收到的报文为:" + text);
	}
}