package org.tain.client;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KieaMonster04Websocket06Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaMonster04Websocket06Application.class, args);
	}

	private static Object waitLock = new Object();
	
	@Override
	public void run(String... args) throws Exception {
		WebSocketContainer container = null;
		Session session = null;
		
		try {
			container = ContainerProvider.getWebSocketContainer();
			session = container.connectToServer(WsClientSocket.class, URI.create("ws://localhost:8080/v0.5/websocket"));
			
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
