package org.tain.websocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.tain.test.TestComponent;

@Deprecated
//@Component
//@ServerEndpoint(value = "/websocket", configurator = CustomSpringConfig.class)
public class _WebSocketServer {

	private Session session;
	
	@OnOpen
	public void onOpen(Session session) {
		_WebSocketServer.onlineCount++;
		this.session = session;
		_WebSocketServer.listeners.add(this);
		System.out.println(">>>>> onOpen called, onlineCount: " + _WebSocketServer.onlineCount);
	}
	
	@OnClose
	public void onClose(Session session) {
		_WebSocketServer.onlineCount--;
		_WebSocketServer.listeners.remove(this);
		System.out.println(">>>>> onClose called, onlineCount: " + _WebSocketServer.onlineCount);
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
			//this.session.getBasicRemote().sendText(message);
			this.session.getAsyncRemote().sendText(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Session getSession() {
		return this.session;
	}
	
	/////////////////////////////////////////////////////////////////////////////
	
	public static Set<_WebSocketServer> listeners = new CopyOnWriteArraySet<>();
	public static int onlineCount = 0;
	
	public static void broadcast(String message) {
		for (_WebSocketServer listener : listeners) {
			System.out.println("----- sessionId: " + listener.getSession().getId());
			listener.sendMessage(message);
		}
	}
}
