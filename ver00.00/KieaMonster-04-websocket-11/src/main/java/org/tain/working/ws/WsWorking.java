package org.tain.working.ws;

import java.net.InetSocketAddress;

import org.java_websocket.server.WebSocketServer;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.ws.SimpleServer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WsWorking {

	public void test00() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("KANG-20210427 >>>>> START of WebSocket Server <<<<< ", CurrentInfo.get());
			
			WebSocketServer server = new SimpleServer(new InetSocketAddress("localhost", 8887));
			server.run();
			
			log.info("KANG-20210427 >>>>> END   of WebSocket Server <<<<< ", CurrentInfo.get());
		}
	}
}
