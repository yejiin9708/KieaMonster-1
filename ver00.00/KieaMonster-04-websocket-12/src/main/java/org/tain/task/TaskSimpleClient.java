package org.tain.task;

import java.net.URI;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.ws.SimpleClient;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskSimpleClient {

	private int index = 0;
	
	private SimpleClient client = null;
	
	@Async
	//@Async("async_Task01_Job01")
	public void asyncTask01Job01(String param) throws Exception {
		log.info("KANG-20200721 >>>>> START {} {} {}", param, index++, CurrentInfo.get());
		
		if (Flag.flag) {
			this.client = new SimpleClient(new URI("ws://localhost:8887/websocket"));
			this.client.connect();  // connect thread
		}
		
		log.info("KANG-20200721 >>>>> END   {} {} {}", param, index++, CurrentInfo.get());
	}
	
	public void sendMessage(String message) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.client.sendMessage(message);
		}
	}
}
