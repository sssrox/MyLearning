package com.example.consumer.service;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.dto.TopicDto;

@Component
public class ConsumerService {
	 @JmsListener(destination = "simple-jms-queue")
	    public void listener(TopicDto msg){
	        System.out.println("Received Message : "+msg.getName());
	    }
	 
	 @JmsListener(destination = "simple-jms-queue2")
	    public void listener2(String msg){
	        System.out.println("Received Message : "+msg);
	    }
}
