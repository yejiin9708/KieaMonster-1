package org.tain.controller;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.tain.config.ws.CustomSpringConfigurator;
import org.tain.tasks.parse.ParseTask;

@Controller
@ServerEndpoint(value = "/websocket", configurator = CustomSpringConfigurator.class)
public class WebSocketServerController {

	//private static Set<Session> sessions = new HashSet<>();
	private static Set<Session> sessions = new CopyOnWriteArraySet<>();
	
	@Autowired
	private ParseTask parseTask;
	
	@Bean
	public void startWebSocketController() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of WebSocketController.");
		
		if (Boolean.TRUE) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println(">>>>> [OnOpen] connection from " + session.getId());
		WebSocketServerController.sessions.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println(">>>>> [OnClose] disconnection from " + session.getId());
		WebSocketServerController.sessions.remove(session);
	}
	
	@OnMessage
	public void onMessage(Session session, String message) {
		System.out.println(">>>>> [OnMessage] recv reqMessage: " + message);
		//this.recvResultTask.setQueueLoadResult(reqMessage);
		
		// parsing and processing
		this.parseTask.parsing(session, message);
		//System.out.println(">>>>> [OnMessage] send resMessage: " + resMessage);
		
		//this.broadCast(replyMessage);
		//this.sendMessage(session, resMessage);
	}
	
	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println(">>>>> [OnError]");
		t.printStackTrace();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public void broadCast(String message) {
		System.out.println(">>>>> broadcast: " + message);
		WebSocketServerController.sessions.forEach(session -> {
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
