package org.tain.working;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

@ClientEndpoint
public class WebSocketClient {

	@OnMessage
	public void onMessage(String message) {
		System.out.println("[OnMessage] recv message: " + message);
	}
}
