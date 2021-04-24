package org.tain.working.ws;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.tain.utils.Flag;

public class SimpleServer extends WebSocketServer {

	private Set<WebSocket> sessions = new HashSet<>();
	
	public SimpleServer(InetSocketAddress inetSocketAddress) {
		super(inetSocketAddress);
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		this.sessions.add(conn);
		int size = this.sessions.size();
		
		conn.send("Welcome to the server!");  // this method sends a message to the new client.
		this.broadcast("new connection: " + handshake.getResourceDescriptor(), this.sessions); // this method sends a message to all clients connected.
		System.out.println("SIZE: (" + size + ") new connection to " + conn.getRemoteSocketAddress() + " " + handshake.getResourceDescriptor());
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		this.sessions.remove(conn);
		int size = this.sessions.size();
		
		System.out.println("SIZE: (" + size + ") closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		System.out.println("received ByteBuffer from " + conn.getRemoteSocketAddress() + " -> " + message);
		if ("quit".equals(message)) {
			conn.close();
		}
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		System.out.println("an error occurred on connection " + conn.getRemoteSocketAddress() + ":" + ex);
	}

	@Override
	public void onStart() {
		System.out.println("server started successfully!");
		
		if (!Flag.flag) {
			/*
			for (int idx=0; ; idx++) {
				String message = String.format(">>>> (%d) TIME [%s]", idx, new Date());
				
				System.out.println("Before broadcast: " + message);
				this.broadcast(message, this.sessions);
				System.out.println("After broadcast... size: " + this.sessions.size());
				
				try { Thread.sleep(3 * 1000); } catch (InterruptedException e) {}
			}
			*/
		}
	}
}
