package com.xiaofan.sell.order.message;


import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {

    String INPUT = "myMessage";

    String INPUT2 = "myMessage2";

   /*@Input(StreamClient.INPUT)
    SubscribableChannel input();*/
/*
    @Output(StreamClient.INPUT)
    MessageChannel output();

    @Input(StreamClient.INPUT2)
    SubscribableChannel input();*/
}
