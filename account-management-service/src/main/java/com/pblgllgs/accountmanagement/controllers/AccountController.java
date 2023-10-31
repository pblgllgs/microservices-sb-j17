package com.pblgllgs.accountmanagement.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/status/check")
    public String status(){
        return "Account Management Status UP!!";
    }

    @GetMapping("/status/checkV2")
    public String statusV2(){
        return "Account Management Status UP V2!!";
    }
}
