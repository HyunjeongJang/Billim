package com.web.billim.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    @GetMapping("/chat/myChatRoom")
    public String myChatRoom() {
        return "pages/chat/chatRoomList";
    }
}
