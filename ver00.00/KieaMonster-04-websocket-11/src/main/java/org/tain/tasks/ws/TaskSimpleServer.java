package org.tain.tasks.ws;

import java.net.InetSocketAddress;
import java.util.Set;

import org.java_websocket.WebSocket;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.ws.SimpleServer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskSimpleServer {

	private int index = 0;
	
	private SimpleServer server = null;
	
	///////////////////////////////////////////////////////////////////////////
	// Async
	@Async("async_Task01_Job01")
	public void asyncTask01Job01(String param) throws Exception {
		log.info("KANG-20200721 >>>>> START {} {} {}", param, index++, CurrentInfo.get());
		
		if (Flag.flag) {
			this.server = new SimpleServer(new InetSocketAddress("localhost", 8887));
			this.server.run();  // run blocking
		}
		
		log.info("KANG-20200721 >>>>> END   {} {} {}", param, index++, CurrentInfo.get());
	}
	
	///////////////////////////////////////////////////////////////////////////
	// send message to all client, that is broadcast
	public void sendMessage(String message) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.server.broadcast(message);
		}
		
		if (!Flag.flag) {
			Set<WebSocket> sessions = this.server.getSessions();
			for (WebSocket session : sessions) {
				this.server.onWebsocketMessage(session, message);
			}
		}
		
		if (!Flag.flag) {
			Set<WebSocket> sessions = this.server.getSessions();
			for (WebSocket session : sessions) {
				this.server.sendMessage(session, message);
			}
		}
	}
}
