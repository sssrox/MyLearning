package com.example.producer.config;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import java.util.Arrays;

import javax.jms.Queue;

@Configuration
@EnableJms
public class JMSConfig {
	@Value("${spring.activemq.broker-url}")
    private String activeMqUrl;

    @Bean
    public Queue queue(){
        return new ActiveMQQueue("simple-jms-queue");
    }
    
    @Bean
    public Queue queue2(){
        return new ActiveMQQueue("simple-jms-queue2");
    }

    @Bean
    public ActiveMQConnectionFactory connectionFatory(){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(activeMqUrl);
        factory.setTrustedPackages(Arrays.asList("com.example.dto"));
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        return new JmsTemplate(connectionFatory());
    }
}
