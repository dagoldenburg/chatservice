package com.rofl.chatservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

public class ChatMessageReader {

    private static final ObjectMapper JSON = new ObjectMapper();

    public static Optional<ChatMessage> read(String value) {
        try {
            final JsonNode node = JSON.readTree(value);

            return Optional.of(new ChatMessage(
                    node.get("messageContent").asText(),
                    node.get("timeStamp").asText(),
                    node.get("sender").asText()
            ));
        } catch (IOException e) {
            System.out.println("wtf");
            return Optional.empty();
        }
    }

}
