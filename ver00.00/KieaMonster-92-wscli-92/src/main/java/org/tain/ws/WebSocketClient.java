package org.tain.ws;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;

import org.springframework.beans.factory.annotation.Autowired;

@ClientEndpoint
public class WebSocketClient {

	@Autowired
	private SimplePrint simplePrint;
	
	private MonWebSocketClient monWebSocketClient;
	
	public WebSocketClient(MonWebSocketClient monWebSocketClient) {
		this.monWebSocketClient = monWebSocketClient;
	}
	
	@OnMessage
	public void onMessage(String message) {
		//System.out.println("[OnMessage] recv message: " + message);
		try {
			this.monWebSocketClient.recvMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@OnClose
	public void onClose() {
		this.simplePrint.print("[OnClose] session close");
		try {
			this.monWebSocketClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@OnError
	public void onError(Throwable t) {
		this.simplePrint.print("[OnError] msg: " + t.getMessage());
	}
}
