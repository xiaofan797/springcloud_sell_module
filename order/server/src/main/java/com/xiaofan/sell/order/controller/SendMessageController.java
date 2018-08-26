package com.xiaofan.sell.order.controller;

import com.xiaofan.sell.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

//    @Autowired
//    StreamClient streamClient;

    @GetMapping("/msg/send")
    public String sendMsg(){
        String msg = "hello World";
//        streamClient.output().send(MessageBuilder.withPayload(msg).build());
        return msg;
    }

}
