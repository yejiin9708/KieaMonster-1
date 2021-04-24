package org.tain.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;

//@Component
@ServerEndpoint(value = "/websocket", configurator = CustomSpringConfig.class)
public class WebSocketServer {

	@Autowired
	private BroadMessage broadMessage;
	
	private Session session;
	
	public Session getSession() {
		return this.session;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		this.broadMessage.addServer(this);
		System.out.println(">>>>> onOpen called, onlineCount: " + this.session.getId());
	}
	
	@OnClose
	public void onClose(Session session) {
		this.broadMessage.removeServer(this);
		System.out.println(">>>>> onClose called, onlineCount: " + this.session.getId());
	}
	
	@OnMessage
	public void onMessage(String message) {
		System.out.println(">>>>> onMessage called, message: " + message);
		this.broadMessage.broadcast(message);
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println(">>>>> onError called, error: " + this.session.getId());
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	
	public void sendMessage(String message) {
		try {
			this.session.getBasicRemote().sendText(message);
			//this.session.getAsyncRemote().sendText(message);
			System.out.printf("SEND ----> sessionId: %s: %s%n", this.session.getId(), message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
