package org.tain.controller;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;

@Controller
@ServerEndpoint(value = "/websocket")
public class WebSocketController {

	private static Set<Session> sessions = new HashSet<>();
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println(">>>>> [OnOpen] connection from " + session.getId());
		WebSocketController.sessions.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println(">>>>> [OnClose] disconnection from " + session.getId());
		WebSocketController.sessions.remove(session);
	}
	
	@OnMessage
	public void onMessage(Session session, String message) {
		System.out.println(">>>>> [OnMessage] recv message: " + message);
		String replyMessage = "echo .... " + message;
		System.out.println(">>>>> [OnMessage] send message: " + replyMessage);
		this.broadCast(replyMessage);
	}
	
	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println(">>>>> [OnError]");
		t.printStackTrace();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void broadCast(String message) {
		WebSocketController.sessions.forEach(session -> {
			this.sendMessage(session, message);
		});
	}
	
	private void sendMessage(Session session, String message) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
