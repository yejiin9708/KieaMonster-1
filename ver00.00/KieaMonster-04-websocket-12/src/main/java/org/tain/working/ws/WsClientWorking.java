package org.tain.working.ws;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WsClientWorking {

	public void test() {
		log.info("KANG-20210405 >>>>> START <<<<< {} {}", CurrentInfo.get());
		
		try {
			WebSocketClient client = new EmptyClient(new URI("ws://localhost:8887/websocket"));
			client.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		log.info("KANG-20210405 >>>>> END <<<<< {} {}", CurrentInfo.get());
	}
}
