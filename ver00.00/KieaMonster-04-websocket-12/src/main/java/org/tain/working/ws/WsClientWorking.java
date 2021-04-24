package org.tain.working.ws;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WsClientWorking {

	public void test() {
		log.info("KANG-20210405 >>>>> START <<<<< {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				WebSocketClient client = new SimpleClient(new URI("ws://localhost:8887/websocket"));
				client.connect();  // connect thread
				
				try { Thread.sleep(3 * 1000); } catch (InterruptedException e) {}
				client.send("################ Hello, world !! ###################");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.flag) {
			/*
			// deep conclusion
			for (int i=0; i < 10; i++) {
				try {
					WebSocketClient client = new SimpleClient(new URI("ws://localhost:8887/websocket"));
					client.connect();  // connect blocking
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try { Thread.sleep(10 * 1000); } catch (InterruptedException e) {}
				log.info("KANG-20210405 >>>>> again, try to connecting");
			}
			*/
		}
		
		log.info("KANG-20210405 >>>>> END <<<<< {} {}", CurrentInfo.get());
	}
}
