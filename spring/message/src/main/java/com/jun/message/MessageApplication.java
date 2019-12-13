package com.jun.message;

import com.jun.message.domain.Spitter;
import com.jun.message.domain.Spittle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import java.util.Date;

@SpringBootApplication
public class MessageApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MessageApplication.class, args);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        System.out.println("send message");
        Spitter spitter = new Spitter(1l,"username","password","spittle","spittle@email.com",false);
        Spittle spittle = new Spittle(1l,spitter,"message", new Date());
        jmsTemplate.convertAndSend("spittle.queue", spittle);
    }

}
