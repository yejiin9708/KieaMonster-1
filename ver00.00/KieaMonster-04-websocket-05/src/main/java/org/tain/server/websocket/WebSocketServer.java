package org.tain.server.websocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.server.component.TestComponent;
import org.tain.server.config.CustomSpringConfig;

@Component
//@ServerEndpoint(value = "/websocket")
//@ServerEndpoint(value = "/websocket", configurator = SpringContext.class)
@ServerEndpoint(value = "/websocket", configurator = CustomSpringConfig.class)
public class WebSocketServer {

	private Session session;
	
	@OnOpen
	public void onOpen(Session session) {
		onlineCount++;
		this.session = session;
		listeners.add(this);
		System.out.println(">>>>> onOpen called, onlineCount: " + onlineCount);
	}
	
	@OnClose
	public void onClose(Session session) {
		onlineCount--;
		listeners.remove(this);
		System.out.println(">>>>> onClose called, onlineCount: " + onlineCount);
	}
	
	@Autowired
	private TestComponent testComponent;
	
	@OnMessage
	public void onMessage(String message) {
		System.out.println(">>>>> onMessage called, message: " + message);
		try {
			this.testComponent.print(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		broadcast(message);
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		//onlineCount--;
		//listeners.remove(this);
		System.out.println(">>>>> onError called, error: " + throwable.getMessage());
	}
	
	private void sendMessage(String message) {
		try {
			this.session.getBasicRemote().sendText(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Set<WebSocketServer> listeners = new CopyOnWriteArraySet<>();
	private static int onlineCount = 0;
	
	public static void broadcast(String message) {
		for (WebSocketServer listener : listeners) {
			listener.sendMessage(message);
		}
	}
}
