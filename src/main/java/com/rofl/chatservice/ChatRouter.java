package com.rofl.chatservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class ChatRouter {

  private ArrayList<ChatMessage> messages = new ArrayList<>();

  @Bean
  RouterFunction<ServerResponse> route() {
    return RouterFunctions
        .route(RequestPredicates.POST("/hello")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), this::postMessage);
  }

  Mono<ServerResponse> postMessage(ServerRequest request) {
   request.bodyToMono(Integer.class)
        .doOnSuccess(this::readAndAddMessage)
        .subscribe();

    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
        .body(BodyInserters.fromObject("hello"));
  }

  void print(Throwable request) {
    log.info("hej tony");
  }

  void readAndAddMessage(Integer message) {
    log.info("hej tsdfsdfsny");

    //read(message).ifPresent(messages::add);
  }

  static Optional<ChatMessage> read(String value) {
    log.info("readyy");
    ObjectMapper JSON = new ObjectMapper();
    try {
      final JsonNode node = JSON.readTree(value);

      return Optional.of(new ChatMessage(
          node.get("messageContent").asText(),
          node.get("timeStamp").asText(),
          node.get("sender").asText()
      ));
    } catch (IOException e) {
      log.info("wtf");
      return Optional.empty();
    }
  }

}
