package org.tain.websocket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class BroadMessage {

	private static Map<String, WebSocketServer> mapConn = new HashMap<>();
	
	public void addServer(WebSocketServer server) {
		mapConn.put(server.getSession().getId(), server);
	}
	
	public void removeServer(WebSocketServer server) {
		mapConn.remove(server.getSession().getId());
	}
	
	public void broadcast(String message) {
		for (Map.Entry<String, WebSocketServer> entry : mapConn.entrySet()) {
			System.out.println("----- sessionId: " + entry.getKey());
			entry.getValue().sendMessage(message);
		}
	}
}
