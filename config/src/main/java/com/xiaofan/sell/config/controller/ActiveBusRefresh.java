package com.xiaofan.sell.config.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class ActiveBusRefresh {

    @PostMapping("/bus-refresh")
    public String busRefresh(@RequestBody String message){
        //log.info("message={}",message);
        return "forward:/actuator/bus-refresh";
    }
}
