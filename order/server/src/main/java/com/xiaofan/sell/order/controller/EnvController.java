package com.xiaofan.sell.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
public class EnvController {

    @Value("${env}")
    private String env;

    @GetMapping("/env/print")
    public String print(){
        return env;
    }
}
