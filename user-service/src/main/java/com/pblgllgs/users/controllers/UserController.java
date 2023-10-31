package com.pblgllgs.users.controllers;

import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private final ServletWebServerApplicationContext applicationContext;

    public UserController(ServletWebServerApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("/status/check")
    public String status(){
        return "Users Status UP on " + applicationContext.getWebServer().getPort();
    }
}
