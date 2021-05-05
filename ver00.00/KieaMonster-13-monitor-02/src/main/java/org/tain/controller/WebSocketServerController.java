package org.tain.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
import org.tain.utils.Flag;

@Controller
@ServerEndpoint(value = "/websocket", configurator = CustomSpringConfigurator.class)
public class WebSocketServerController {

	@Autowired
	private ParseTask parseTask;
	
	//private static Set<Session> sessions = new HashSet<>();
	//private static Set<Session> sessions = new CopyOnWriteArraySet<>();
	//private static Map<String, Session> mapSession = new HashMap<>();
	//private static Map<String, Session> peers = Collections.synchronizedMap(mapSession);
	private static Map<String, Session> peers = Collections.synchronizedMap(new HashMap<>());
	
	@Bean("startWebSocketController")
	public void startWebSocketController() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of WebSocketServerController.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.printf(">>>>> [OnOpen-%s] connection\n", session.getId());
		//WebSocketServerController.sessions.add(session);
		WebSocketServerController.peers.put(session.getId(), session);
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.printf(">>>>> [OnClose-%s] disconnection\n", session.getId());
		WebSocketServerController.peers.remove(session.getId());
	}
	
	@OnMessage
	public void onMessage(Session session, String reqMessage) {
		System.out.printf(">>>>> [OnMessage-%s] recv reqMessage: %s\n", session.getId(), reqMessage);
		//this.recvResultTask.setQueueLoadResult(reqMessage);
		
		// parsing and processing
		this.parseTask.parsing(session, reqMessage);
		//System.out.println(">>>>> [OnMessage] send resMessage: " + resMessage);
		
		//this.broadCast(replyMessage);
		//this.sendMessage(session, resMessage);
	}
	
	@OnError
	public void onError(Session session, Throwable t) {
		System.out.printf(">>>>> [OnError-%s] error\n", session.getId());
		t.printStackTrace();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Deprecated
	public void _printSessionId() {
		WebSocketServerController.peers.forEach((sessionId, session) -> {
			System.out.printf(">>>>> [sessionId] %s - %s\n", sessionId, session.getId());
		});
	}
	
	@Deprecated
	public void _broadCast(String message) {
		System.out.println(">>>>> [broadcast] " + message);
		WebSocketServerController.peers.forEach((sessionId, session) -> {
			this._sendMessage(session, message);
		});
	}
	
	@Deprecated
	public void _sendMessage(Session session, String message) {
		if (Flag.flag) System.out.printf(">>>>> [sendMessage-%s] %s\n", session.getId(), message);
		try {
			session.getBasicRemote().sendText(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static void printSessionId() {
		WebSocketServerController.peers.forEach((sessionId, session) -> {
			System.out.printf(">>>>> [sessionId] %s - %s\n", sessionId, session.getId());
		});
	}
	
	public static Session findSessionById(String sessionId) {
		if (WebSocketServerController.peers.containsKey(sessionId)) {
			return WebSocketServerController.peers.getOrDefault(sessionId, null);
		}
		return null;
	}
	
	public static void broadCast(String message) {
		System.out.println(">>>>> [broadcast] " + message);
		WebSocketServerController.peers.forEach((sessionId, session) -> {
			WebSocketServerController.sendMessage(session, message);
		});
	}
	
	public static void sendMessage(String sessionId, String message) {
		Session session = WebSocketServerController.findSessionById(sessionId);
		WebSocketServerController.sendMessage(session, message);
	}
	
	public static void sendMessage(Session session, String message) {
		if (Flag.flag) System.out.printf(">>>>> [sendMessage-%s] %s\n", session.getId(), message);
		try {
			session.getBasicRemote().sendText(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
