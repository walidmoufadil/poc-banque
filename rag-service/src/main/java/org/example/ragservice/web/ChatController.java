package org.example.ragservice.web;

import org.example.ragservice.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    @GetMapping("/ask")
    public String ask(String message) {
        return chatService.chat(message);
    }
}
