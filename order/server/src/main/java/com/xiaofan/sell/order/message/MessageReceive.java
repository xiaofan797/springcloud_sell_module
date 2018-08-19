package com.xiaofan.sell.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageReceive {
    //    @RabbitListener(queues = "chinaQueue")
//    @RabbitListener(queuesToDeclare = @Queue("chinaQueue"))
    //自动创建，Exchange和Queue绑定
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue("chinaQueue"),
                    exchange = @Exchange("football"),
                    key = {"china"})
    })
    public void receiveChina(String message) {
        log.info("chinaMsg={}", message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue("usaQueue"),
                    exchange = @Exchange("football"),
                    key = {"usa"})
    })
    public void receiveUsa(String message) {
        log.info("usaMsg={}", message);
    }

}
