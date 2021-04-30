package org.tain.ws;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MonWebSocketClient {

	@Autowired
	private SimplePrint simplePrint;
	
	private Session session = null;
	
	@Bean
	public void start() throws Exception {
		this.simplePrint.print(">>>>> Start MonWebSocketClient.....");
		
		try {
			WebSocketClient webSocketClient = new WebSocketClient(this);
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			this.session = container.connectToServer(webSocketClient, URI.create("ws://localhost:8091/v0.1/websocket"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void recvMessage(String message) throws Exception {
		this.simplePrint.print("[recvMessage] message: " + message);
	}
	
	public void sendMessage(String message) throws Exception {
		this.simplePrint.print("[sendMessage] message: " + message);
		this.session.getAsyncRemote().sendText(message);
	}
	
	public void close() throws Exception {
		this.session.close();
	}
}
