package org.tain.tasks.linkMonitor;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;

@ClientEndpoint
public class WebSocketClient {

	private LinkMonitorTask sendResultTask = null;
	
	public WebSocketClient(LinkMonitorTask sendResultTask) {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of WebSocketClient.");
		this.sendResultTask = sendResultTask;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@OnMessage
	public void onMessage(String message) {
		System.out.println("[OnMessage] recv message: " + message);
		
		if (Boolean.TRUE) {
			if (this.sendResultTask != null) {
				this.sendResultTask.recvMessage(message);
			} else {
				System.out.println("##### WebSocketClient.parseTask is null");
			}
		}
	}
	
	@OnClose
	public void onClose() {
		System.out.println("[OnClose] session close");
	}
	
	@OnError
	public void onError(Throwable t) {
		System.out.println("[OnError] msg: " + t.getMessage());
	}
}
