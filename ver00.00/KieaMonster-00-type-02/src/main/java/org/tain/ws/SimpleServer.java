package org.tain.ws;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class SimpleServer extends WebSocketServer {

	public SimpleServer(InetSocketAddress inetSocketAddress) {
		super(inetSocketAddress);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private Set<WebSocket> sessions = new HashSet<>();
	
	public Set<WebSocket> getSessions() {
		return this.sessions;
	}
	
	public void sendMessage(WebSocket session, String message) {
		session.send(message);
		System.out.println("[sendMessage] [SVR -> CLI] " + message);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Override
	public void onStart() {
		System.out.println("[onStart] server started successfully!");
	}
	
	@Override
	public void onOpen(WebSocket session, ClientHandshake handshake) {
		this.sessions.add(session);
		int size = this.sessions.size();
		
		String msg = "Welcome to the server!";
		session.send(msg);  // this method sends a message to the new client.
		System.out.println("[onOpen] [SVR -> CLI] [send] " + msg);
		
		msg = "SIZE: (" + size + ") new connection to " + session.getRemoteSocketAddress() + " " + handshake.getResourceDescriptor();
		this.broadcast(msg, this.sessions); // this method sends a message to all clients connected.
		System.out.println("[onOpen] [SVR -> CLI] [broadcast] " + msg);
	}

	@Override
	public void onClose(WebSocket session, int code, String reason, boolean remote) {
		this.sessions.remove(session);
		int size = this.sessions.size();
		
		System.out.println("[onClose] SIZE: (" + size + ") closed " + session.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
	}

	@Override
	public void onMessage(WebSocket session, String message) {
		System.out.println("[onMessage] [CLI -> SVR] " + session.getRemoteSocketAddress() + " -> " + message);
		//if ("quit".equals(message)) {
		//	conn.close();
		//}
	}

	@Override
	public void onError(WebSocket session, Exception ex) {
		System.out.println("[onError] an error occurred on connection " + session.getRemoteSocketAddress() + ":" + ex);
	}
}
