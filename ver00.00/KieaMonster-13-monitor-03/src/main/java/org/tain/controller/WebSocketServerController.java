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
import org.tain.vo.SessionInfo;

/*
 * uri: ws://localhost:8080/v0.1/websocket
 */
@Controller
@ServerEndpoint(value = "/websocket", configurator = CustomSpringConfigurator.class)
public class WebSocketServerController {

	@Autowired
	private ParseTask parseTask;
	
	//private static Set<Session> sessions = new HashSet<>();
	//private static Set<Session> sessions = new CopyOnWriteArraySet<>();
	//private static Map<String, Session> mapSession = new HashMap<>();
	//private static Map<String, Session> peers = Collections.synchronizedMap(mapSession);
	public static Map<String, SessionInfo> peers = Collections.synchronizedMap(new HashMap<>());
	
	@Bean("startWebSocketController")
	public void startWebSocketController() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of WebSocketServerController.");
		
		if (Boolean.TRUE) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.printf(">>>>> [OnOpen-%s] connection\n", session.getId());
		WebSocketServerController.peers.put(session.getId(), SessionInfo.builder().session(session).build());
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.printf(">>>>> [OnClose-%s] disconnection\n", session.getId());
		peers.remove(session.getId());
	}
	
	@OnMessage
	public void onMessage(Session session, String reqMessage) {
		System.out.printf(">>>>> [OnMessage-%s] recv reqMessage: %s\n", session.getId(), reqMessage);
		SessionInfo sessionInfo = peers.get(session.getId());
		
		// parsing and processing
		this.parseTask.parsing(sessionInfo, reqMessage);
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
		peers.forEach((sessionId, sessionInfo) -> {
			System.out.printf(">>>>> [sessionId] %s - %s\n", sessionId, sessionInfo.getSession().getId());
		});
	}
	
	@Deprecated
	public void _broadCast(String message) {
		System.out.println(">>>>> [broadcast] " + message);
		peers.forEach((sessionId, sessionInfo) -> {
			this._sendMessage(sessionInfo.getSession(), message);
		});
	}
	
	@Deprecated
	public void _sendMessage(Session session, String message) {
		if (Boolean.TRUE) System.out.printf(">>>>> [sendMessage-%s] %s\n", session.getId(), message);
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
		peers.forEach((sessionId, sessionInfo) -> {
			System.out.printf(">>>>> [sessionId] %s - %s\n", sessionId, sessionInfo.getSession().getId());
		});
	}
	
	public static SessionInfo findSessionById(String sessionId) {
		if (peers.containsKey(sessionId)) {
			return peers.getOrDefault(sessionId, null);
		}
		return null;
	}
	
	public static void broadCast(String message) {
		System.out.println(">>>>> [broadcast] " + message);
		peers.forEach((sessionId, sessionInfo) -> {
			sendMessage(sessionInfo.getSession(), message);
		});
	}
	
	public static void sendMessage(String sessionId, String message) {
		SessionInfo sessionInfo = findSessionById(sessionId);
		sendMessage(sessionInfo.getSession(), message);
	}
	
	public static void sendMessage(Session session, String message) {
		if (Boolean.TRUE) System.out.printf(">>>>> [sendMessage-%s] %s\n", session.getId(), message);
		try {
			session.getBasicRemote().sendText(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
