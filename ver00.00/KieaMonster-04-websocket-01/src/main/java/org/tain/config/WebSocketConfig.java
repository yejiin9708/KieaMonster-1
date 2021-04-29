package org.tain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
	
	/*
	@Bean
	public ChatServerEndpoint chatServerEndpoint() {
		return new ChatServerEndpoint();
	}
	*/
}
