package com.rofl.chatservice;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Value
public class ChatMessage {

    @NotNull
    private String messageContent;
    @NotNull
    private String timeStamp;
    @NotBlank
    private String sender;
}
