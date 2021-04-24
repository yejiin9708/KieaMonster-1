package org.tain.working.ws;

import java.net.InetSocketAddress;

import org.java_websocket.server.WebSocketServer;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WsServerWorking {

	public void test() {
		log.info("KANG-20210405 >>>>> START <<<<< {} {}", CurrentInfo.get());
		
		String host = "localhost";
		int port = 8887;
		
		WebSocketServer server = new SimpleServer(new InetSocketAddress(host, port));
		server.run();
		
		log.info("KANG-20210405 >>>>> END <<<<< {} {}", CurrentInfo.get());
	}
}
