package com.semester.tinder.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class TestController {

    @MessageMapping("/application")
    @SendTo("/all/messages")
    public String send(final String message) throws Exception {
        return message + "handled";
    }

}