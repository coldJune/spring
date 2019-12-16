package com.jun.websocket.service.impl;

import com.jun.websocket.model.Notification;
import com.jun.websocket.model.Spittle;
import com.jun.websocket.service.SpittleFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SpittleFeedServiceImpl implements SpittleFeedService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private Pattern  pattern = Pattern.compile("\\@(\\S+)");
    @Override
    public void broadcastSpittle(Spittle spittle) {
        messagingTemplate.convertAndSend("/topic/spittlefeed", spittle);
        Matcher matcher = pattern.matcher(spittle.getMessage());
        if(matcher.find()){
            String username = matcher.group(1);
            messagingTemplate.convertAndSendToUser(username,
                    "/queue/notification",
                    new Notification("You just get mentioned"));
        }
    }
}
