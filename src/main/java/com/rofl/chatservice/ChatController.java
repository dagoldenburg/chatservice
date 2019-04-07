package com.rofl.chatservice;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ChatController {

    final FluxProcessor processor;
    final FluxSink sink;
    AtomicLong counter;


    private ArrayList<ChatMessage> messages = new ArrayList<>();

    public ChatController(FluxProcessor processor, FluxSink sink) {
        this.processor = processor;
        this.sink = sink;
        this.counter = new AtomicLong();
    }

    @PostMapping("/postMessage")
    private void postMessage(@Valid @RequestBody ChatMessage message) { ;
        messages.add(message);
        sink.next(message);
    }

    @RequestMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent> subscriptionList() {
        return processor
                .map(e -> ServerSentEvent.builder(e).build());
    }


}
