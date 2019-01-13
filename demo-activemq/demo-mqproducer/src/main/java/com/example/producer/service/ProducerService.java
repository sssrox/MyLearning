package com.example.producer.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.TopicDto;

import javax.jms.Queue;

@RestController
public class ProducerService {
	@Autowired
    private Queue queue;
	
	@Autowired
    private Queue queue2;

    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping( value ="publish",  method=RequestMethod.POST)
    public String publish(@RequestBody TopicDto msg){
    	System.out.println("received message: "+msg.getName());
        jmsTemplate.convertAndSend(queue,msg);
        return "Your message <b>"+msg.getName()+"</b> published successfully";
    }
    
    @GetMapping("publish2/{msg}")
    public String publish2(@PathVariable("msg") final String msg){
        jmsTemplate.convertAndSend(queue2,msg);
        return "Your message <b>"+msg+"</b> published super successfully";
    }
}
