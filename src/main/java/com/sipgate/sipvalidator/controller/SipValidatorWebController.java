package com.sipgate.sipvalidator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SipValidatorWebController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}