package com.rofl.chatservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;

@Configuration
public class ChatRouter {

    private ArrayList<ChatMessage> messages = new ArrayList<>();

    @Bean
    RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(RequestPredicates.POST("/hello").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), this::postMessage);
    }

     Mono<ServerResponse> postMessage(ServerRequest request) {
        request.bodyToMono(String.class)
                .doOnNext(this::readAndAddMessage)
                .doOnSuccess(asd -> handleAsd())
                .doOnError(asd -> handleError())
                .subscribe();

        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(BodyInserters.fromObject("hello"));
    }

    void handleError() {
        System.out.println("error");
    }

    void handleAsd() {
        System.out.println("success " + messages.size());
    }

     void readAndAddMessage(String message) {
         Optional<ChatMessage> read = ChatMessageReader
                 .read(message);
     }

}
