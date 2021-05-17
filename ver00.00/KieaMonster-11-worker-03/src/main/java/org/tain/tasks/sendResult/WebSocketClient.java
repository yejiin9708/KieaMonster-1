package org.tain.tasks.sendResult;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;

import org.tain.tasks.parse.ParseTask;

@ClientEndpoint
public class WebSocketClient {

	private ParseTask parseTask = null;
	
	public WebSocketClient(ParseTask parseTask) {
		//System.out.println("KANG-20210405 >>>>> Hello, Starting of WebSocketClient.");
		this.parseTask = parseTask;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@OnMessage
	public void onMessage(String message) {
		System.out.println(">>>>> [OnMessage] recv message: " + message);
		
		if (Boolean.TRUE) {
			if (this.parseTask != null) {
				this.parseTask.parsing(message);
			} else {
				System.out.println("##### WebSocketClient.parseTask is null");
			}
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
