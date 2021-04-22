package org.tain.client.websocket;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

import org.tain.client.component.TestComponent;

@ClientEndpoint
public class WebSocketClient {

	//@Autowired
	//private TestComponent testComponent;
	
	@OnMessage
	public void onMessage(String message) {
		System.out.println(">>>>> WebSocketClient.onMessage: " + message);
		try {
			//this.testComponent.print(message);
			TestComponent.print2(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
