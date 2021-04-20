package org.tain.client;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

@ClientEndpoint
public class WsClientSocket {

	@OnMessage
	public void onMessage(String message) {
		System.out.println(">>>>> received message: " + message);
	}
}
