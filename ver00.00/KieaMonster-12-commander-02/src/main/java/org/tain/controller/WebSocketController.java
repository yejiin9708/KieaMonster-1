package org.tain.controller;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.tain.tasks.recvresult.RecvResultTask;
import org.tain.utils.Flag;

@Controller
@ServerEndpoint(value = "/websocket")
@DependsOn(value = {"LoadTablesTask", "RecvResultTask", "SendResultTask"})
public class WebSocketController {

	@Bean
	public void startWebSocketController() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of WebSocketController.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private RecvResultTask recvResultTask;
	
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
	
	//@PostConstruct
	@OnMessage
	public void onMessage(Session session, String message) {
		System.out.println(">>>>> [OnMessage] recv message: " + message);
		this.recvResultTask.setQueueLoadResult(message);
		
		String replyMessage = "echo .... " + message;
		System.out.println(">>>>> [OnMessage] send message: " + replyMessage);
		//this.broadCast(replyMessage);
		this.sendMessage(session, replyMessage);
	}
	
	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println(">>>>> [OnError]");
		t.printStackTrace();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public void broadCast(String message) {
		System.out.println(">>>>> broadcast: " + message);
		WebSocketController.sessions.forEach(session -> {
			this.sendMessage(session, message);
		});
	}
	
	public void sendMessage(Session session, String message) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
