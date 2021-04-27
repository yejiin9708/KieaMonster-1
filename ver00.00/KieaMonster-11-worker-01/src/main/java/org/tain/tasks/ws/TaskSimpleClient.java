package org.tain.tasks.ws;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.commands.RecvCommands;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.ws.SimpleClient;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskSimpleClient {

	@Autowired
	private RecvCommands recvCommands;
	
	private int index = 0;
	
	private SimpleClient client = null;
	
	//@Async
	@Async("async_Task01_Client01")
	public void asyncTask01Client01(String param) throws Exception {
		log.info("KANG-20200721 >>>>> START {} {} {}", param, index++, CurrentInfo.get());
		
		if (Flag.flag) {
			this.client = new SimpleClient(new URI("ws://localhost:8887/websocket"), recvCommands);
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
