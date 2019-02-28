package com.rofl.chatservice;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/v1")
public class ChatController {
/*

    private ArrayList<ChatMessage> messages = new ArrayList<>();

    @PostMapping("/postMessage")
    private Mono<Void> postMessage(@Valid @RequestBody ChatMessage message) {
        messages.add(message);
        return Mono.empty();
    }

    @GetMapping
    private Flux<ChatMessage> subscribeToMessageList() {
        return null;
    }
*/

}
