package org.tain;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.websocket.WebSocketClient;

@SpringBootApplication
public class KieaMonster04Wscli01Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaMonster04Wscli01Application.class, args);
	}

	/////////////////////////////////////////////////////////////
	
	private static Object waitLock = new Object();
	
	@Override
	public void run(String... args) throws Exception {
		Session session = null;
		
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			session = container.connectToServer(WebSocketClient.class, URI.create("ws://localhost:8081/v0.1/websocket"));
			session.getAsyncRemote().sendText("Hello, world!!!!");
			
			//try { Thread.sleep(60 * 1000); } catch (InterruptedException e) {}
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
