package com.jun.websocket.controller;

import com.jun.websocket.model.Notification;
import com.jun.websocket.model.Spittle;
import com.jun.websocket.model.SpittleForm;
import com.jun.websocket.service.SpittleFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Date;

@Controller
public class SpittleController {
    @Autowired
    private SpittleFeedService spittleFeedService;

    @MessageMapping("/spittle")
    @SendToUser("/queue/notification")
    public Notification handleSpittle(Principal principal, SpittleForm spittleForm){
        Spittle spittle = new Spittle(principal.getName(), spittleForm.getText(), new Date());
        spittleFeedService.broadcastSpittle(spittle);
        return new Notification("saved spittle for user:"+ principal.getName());
    }
}
