package org.tain.component;

import org.springframework.stereotype.Component;

@Component
public class StartWebSocket {

	public void start() throws Exception {
		System.out.println("Hello, WebSocketClient.....");
		
		//WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		//Session session = container.connectToServer(WebSocketClient.class, URI.create("ws://localhost:8091/v0.1/websocket"));
		//session.getAsyncRemote().sendText("Hello, world!!!");
	}
}
