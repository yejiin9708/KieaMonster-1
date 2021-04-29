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
@ServerEndpoint(value = "/chat")
public class WebSocketController {

	private static Set<Session> setSessions = new HashSet<>();
	
	@OnOpen
	public void onOpen(Session session) {
	//public void onOpen(Session session, @PathParam("userId") String userId) {
		System.out.println(">>>>> [OnOpen] session.getId(): " + session.getId());
		setSessions.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println(">>>>> [OnClose]");
		setSessions.remove(session);
	}
	@OnMessage
	public void onMessage(Session session, String message) {
		System.out.println(">>>>> [OnMessage] message: " + message);
		
		System.out.println("* recv message: " + message);
		String replyMessage = "echo.. " + message;
		System.out.println("* send message: " + replyMessage);
		this.broadCast(replyMessage);
	}
	
	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println(">>>>> [OnError]");
		t.printStackTrace();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void broadCast(String message) {
		setSessions.forEach(session -> {
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
