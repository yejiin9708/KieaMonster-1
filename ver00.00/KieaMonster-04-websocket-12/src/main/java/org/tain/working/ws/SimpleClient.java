package org.tain.working.ws;

import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

public class SimpleClient extends WebSocketClient {

	public SimpleClient(URI serverUri, Draft draft) {
		super(serverUri, draft);
	}
	
	public SimpleClient(URI serverUri) {
		super(serverUri);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		this.send("Hello, it is me. Mario :)");
		System.out.println("new connection opened");
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("closed with exit code: " + code + " additional info: " + reason);
	}
	
	@Override
	public void onMessage(String message) {
		System.out.println("received message: " + message);
		if ("quit".equals(message)) {
			this.close();
		}
	}
	
	@Override
	public void onMessage(ByteBuffer bytes) {
		System.out.println("received ByteBuffer.");
	}

	@Override
	public void onError(Exception ex) {
		System.out.println("an error occurred: " + ex);
	}
}
