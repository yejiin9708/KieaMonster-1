package org.tain;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.working.WebSocketClient;

@SpringBootApplication
public class KieaMonster92Wscli92Application {
//public class KieaMonster92Wscli92Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaMonster92Wscli92Application.class, args);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private static Object waitLock = new Object();
	
	//@Override
	public void run(String... args) throws Exception {
		//this.startWebSocket.start();
		
		Session session = null;
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			//session = container.connectToServer(WebSocketClient.class, URI.create("ws://localhost:8091/v0.1/websocket"));
			session = container.connectToServer(new WebSocketClient(), URI.create("ws://localhost:8091/v0.1/websocket"));
			session.getAsyncRemote().sendText("Hello, world!!!!");
			
			synchronized (waitLock) {
				try {
					waitLock.wait();
				} catch (InterruptedException e) {}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				try { session.close(); } catch (Exception e) {}
			}
		}
	}
}
