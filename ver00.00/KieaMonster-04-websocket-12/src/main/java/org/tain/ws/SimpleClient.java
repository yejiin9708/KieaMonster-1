package org.tain.ws;

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
		System.out.println("[onOpen] new connection opened");
		
		String msg = "Hello, it is me. Mario :)";
		this.send(msg);
		System.out.println("[onOpen] [CLI -> SVR] " + msg);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("[onClose] closed with exit code: " + code + " additional info: " + reason);
		System.exit(0);
	}
	
	@Override
	public void onMessage(String message) {
		System.out.println("[onMessage] [SVR -> CLI] " + message);
		//if ("quit".equals(message)) {
		//	this.close();
		//}
	}
	
	@Override
	public void onMessage(ByteBuffer bytes) {
		System.out.println("[onMessage] [SVR -> CLI].ByteBuffer " + bytes.toString());
	}

	@Override
	public void onError(Exception ex) {
		System.out.println("[onError] an error occurred: " + ex);
	}
	
	public void sendMessage(String message) {
		this.send(message);
		System.out.println("[sendMessage] [CLI -> SVR] " + message);
	}
}
