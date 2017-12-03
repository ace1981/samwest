package com.hujao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hujao.model.UserModel;
import com.hujao.repository.UserRepository;
import com.hujao.util.JSON;  
  
// 双向队列 实现应答模式
@Component  
public class AckQueueConsumer {
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息   
    @JmsListener(destination = "registuser4email.queue",containerFactory = "jmsListenerContainerQueue")  
    @SendTo("sendemail.replyqueue")
    public String receiveEmailAckQueue(String text) {  
        System.out.println("registuser4email.queue收到的报文为:"+text);  
        UserModel model=JSON.toBean(text, UserModel.class);
        //TODO ： sendemail
        System.out.println("send email to"+model.getUseremail());  
        return "return message "+text;
    }
    
    @JmsListener(destination = "registuser4sms.queue",containerFactory = "jmsListenerContainerQueue")  
    @SendTo("sendsms.replyqueue")
    public String receiveSmsAckQueue(String text) {  
        System.out.println("registuser4sms.queue收到的报文为:"+text);
        UserModel model=JSON.toBean(text, UserModel.class);
        //TODO ： sendsms
        System.out.println("send sms to"+model.getUsermobile()); 
        return "return message "+text;
    }
}