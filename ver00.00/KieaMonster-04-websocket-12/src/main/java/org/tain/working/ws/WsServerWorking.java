package org.tain.working.ws;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.ws.SimpleClient;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WsServerWorking {

	public void test00() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("KANG-20210427 >>>>> START of WebSocket Client <<<<< ", CurrentInfo.get());
			
			try {
				WebSocketClient client = new SimpleClient(new URI("ws://localhost:8887/websocket"));
				client.connect();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			log.info("KANG-20210427 >>>>> END   of WebSocket Client <<<<< ", CurrentInfo.get());
		}
	}
}
