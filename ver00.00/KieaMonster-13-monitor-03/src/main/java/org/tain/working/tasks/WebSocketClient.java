package org.tain.working.tasks;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;

@ClientEndpoint
public class WebSocketClient {

	@OnMessage
	public void onMessage(String message) {
		System.out.println(">>>>> [OnMessage] recv message: " + message);
	}
	
	@OnClose
	public void onClose() {
		System.out.println(">>>>> [OnClose] session close");
	}
	
	@OnError
	public void onError(Throwable t) {
		System.out.println(">>>>> [OnError] msg: " + t.getMessage());
	}
}
