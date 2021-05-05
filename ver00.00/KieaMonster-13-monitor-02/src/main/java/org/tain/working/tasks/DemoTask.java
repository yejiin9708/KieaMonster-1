package org.tain.working.tasks;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.tools.node.MonJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component("DemoTask")
@Slf4j
public class DemoTask {

	@Bean
	public void startDemoTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of DemoTask.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private Session session;
	
	public void recvMessage(String message) throws Exception {
		System.out.println("[recvMessage] message: " + message);
	}
	
	public void sendMessage(String message) throws Exception {
		System.out.println(">>>>> [sendMessage] message: " + message);
		this.session.getAsyncRemote().sendText(message);
	}
	
	public void close() throws Exception {
		this.session.close();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	// DemoTask
	@Async(value = "async_0103")
	public void async0103(String param) throws Exception {
		//Sleep.run(2 * 1000);
		log.info("KANG-20200721 >>>>> async_0103 START {} {}", param, CurrentInfo.get());
		
		if (Flag.flag) {
			// create a connection with websocket
			try {
				WebSocketClient webSocketClient = new WebSocketClient();
				WebSocketContainer container = ContainerProvider.getWebSocketContainer();
				this.session = container.connectToServer(webSocketClient, URI.create("ws://localhost:8093/v0.1/websocket"));
			} catch (Exception e) {
				throw e;
			}
			System.out.println(">>>>> Start WebSocketClient.....");
		}
		
		if (Flag.flag) {
			for (int i=0; i < 10000; i++) {
				Sleep.run(5 * 1000);
				MonJsonNode reqNode = new MonJsonNode("{}");
				reqNode.put("svrCode", "TEST01");
				if (i % 2 == 0) {
					reqNode.put("msgCode", "CMD_RET");
				} else {
					reqNode.put("msgCode", "CMD_HIST");
				}
				reqNode.put("index", String.format("%03d", i));
				
				this.sendMessage(reqNode.toString());
			}
		}
		
		if (Flag.flag) this.close();
	}
}
