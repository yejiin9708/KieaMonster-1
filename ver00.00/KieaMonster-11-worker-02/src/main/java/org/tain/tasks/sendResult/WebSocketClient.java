package org.tain.tasks.sendResult;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.tain.tasks.parse.ParseTask;

@ClientEndpoint
public class WebSocketClient {

	@Autowired
	private ParseTask parseTask;
	
	@OnMessage
	public void onMessage(String message) {
		System.out.println(">>>>> [OnMessage] recv message: " + message);
		this.parseTask.parsing(message);
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
