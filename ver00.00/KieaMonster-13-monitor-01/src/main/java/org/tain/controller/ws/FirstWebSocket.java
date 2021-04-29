package org.tain.controller.ws;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;

/*
 * 안녕하세요. 명월입니다.
 * 
 * 이 글은 Java의 Servlet에서 사용하는 웹 소켓 (WebSocket) 에 대한 글입니다.
 * 
 * 보통의 웹 환경은 브라우저(클라이언트)에서 웹 서버에 Html 문서를 요청하면, 웹 서버는 Html를 작성하고 브라우저(클라이언트)에 응답 한 후 연결을 끊는 비동기 소켓 통신입니다.
 * Websocket는 브라우저(클라이언트)가 접속 요청을 하고 Web 서버가 응답 한 후 연결을 끊는 것이 아니고, Connection을 그대로 유지하고 브라우저(클라이언트)의 요청이 없어도 데이터를 전송할 수있는 프로토콜입니다.
 * 
 * 예를 들어, 채팅을 생각하면 사용자가 내용을 쓰고 서버에 전송합니다. 즉, 브라우저(클라이언트)에서 서버로 데이터를 요청한 것입니다. 그리고 서버에서는 다른 응답 결과가 없으면 그대로 응답 메시지를 보내지 않습니다.
 * 그러나 다른 사용자가 채팅 내용을 쓰고 서버로 전송하면 내 브라우저(클라이언트)로 메시지를 보내야 합니다.
 * 
 * 일반 웹 프로토콜의 경우는 서버와 브라우저(클라이언트)와 연결이 끊긴 상태이기 때문에 브라우저에서 요청이 오지 않는 이상 다른 사용자의 메시지를 보낼 수 없습니다.
 * WebSocket의 경우는 서버와 브라우저(클라이언트)가 끊기지 않은 상태이기 때문에 브라우저(클라이언트)의 요청이 없어도 서버에서 브라우저로 메시지를 보낼 수가 있습니다.
 * 
 * 물론 웹 프로토콜의 경우, 몇 초당 갱신 주기를 만들어서 일정한 시간 단위로 요청을 하게 하여 동기화를 할 수 있지만, 이 경우에는 트래픽이 많아서져서 웹 서버에 부담이 많이 되겠네요.(socket.io)
 * 
 * WebSocket의 경우는 HTML5부터 표준이 되었습니다. 이미 HTML5가 표준이 된지 한참이 되었으니, WebSocket을 지원하지 않는 브라우저는 이제 없겠네요.
 * 프로토콜의 요청은 「ws://~」로 시작합니다. ssl 방식이라면 「wss://~」로 시작합니다.
 */
@Controller
@ServerEndpoint(value = "/first")
public class FirstWebSocket {

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("client is now connected...");
	}
	
	@OnMessage
	public String onMessage(String message) {
		System.out.println("receive from client: " + message);
		String replyMessage = "echo ... " + message;
		System.out.println("send to client: " + replyMessage);
		return replyMessage;
	}
	
	@OnClose
	public void onClose() {
		System.out.println("client is now disconnected...");
	}
	
	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
	}
}
