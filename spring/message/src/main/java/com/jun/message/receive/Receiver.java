package com.jun.message.receive;

import com.jun.message.domain.Spittle;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "spittle.queue", containerFactory = "jmsFactory")
    public void receiveMessage(Spittle spittle){
        System.out.println(spittle.toString());
    }

}
