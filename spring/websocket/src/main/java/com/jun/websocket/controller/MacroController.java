package com.jun.websocket.controller;

import com.jun.websocket.model.Shout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MacroController {
    private Logger log = LoggerFactory.getLogger(MacroController.class);

    @MessageMapping("/hello")
    @SendTo("/topic/world")
    public Shout handleShout(Shout message){
       log.info("Received message:"+ message.getMessage());
       try
       {
           Thread.sleep(2000);
       }catch (InterruptedException e){

       }
       Shout out = new Shout();
       out.setMessage("world");
       return out;
    }
}
