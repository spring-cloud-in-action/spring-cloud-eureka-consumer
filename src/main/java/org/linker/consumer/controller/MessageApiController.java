package org.linker.consumer.controller;

import org.linker.consumer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RWM
 * @date 2018/11/28
 */
@RestController
public class MessageApiController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/send/message")
    public void sendMessage() {
        messageService.sendMessage();
    }

}
