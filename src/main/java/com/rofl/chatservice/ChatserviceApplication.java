package com.rofl.chatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;

@SpringBootApplication
public class ChatserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatserviceApplication.class, args);
	}

	@Bean
	public FluxProcessor fluxProcessor() {
		return DirectProcessor.create().serialize();
	}

	@Bean
	public FluxSink fluxSink(FluxProcessor fluxProcessor) {
		return fluxProcessor.sink();
	}

}
