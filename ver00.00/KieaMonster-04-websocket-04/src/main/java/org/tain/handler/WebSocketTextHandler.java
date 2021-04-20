package org.tain.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketTextHandler extends TextWebSocketHandler {

	// ArrayList<WebSocketSession> sessions = new ArrayList<>();
	private Map<String, WebSocketSession> sessions = new HashMap<>();

	// 세션이 생성될때 시작되는 함수
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		this.sessions.put(session.getId(), session);
	}

	// client에서 메시지가 서버로 전송댈때 실행되는 함수.
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		String payload = message.getPayload();

		try {
			// 접속된 모든 세션에 메시지 전송
			for (String key : this.sessions.keySet()) {
				WebSocketSession ss = this.sessions.get(key);
				ss.sendMessage(new TextMessage(payload));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 세션이 끝날때 실행되는 함수
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		this.sessions.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}
}
