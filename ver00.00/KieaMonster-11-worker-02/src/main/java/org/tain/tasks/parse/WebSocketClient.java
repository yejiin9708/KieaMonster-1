package org.tain.tasks.parse;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;

import org.tain.utils.Flag;

@ClientEndpoint
public class WebSocketClient {

	private ParseTask parseTask;
	
	public WebSocketClient(ParseTask parseTask) {
		this.parseTask = parseTask;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@OnMessage
	public void onMessage(String message) {
		System.out.println(">>>>> [OnMessage] recv message: " + message);
		
		if (Flag.flag) {
			if (this.parseTask == null) {
				System.out.println("##### WebSocketClient.parseTask is null");
			}
			this.parseTask.parsing(message);
		}
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
