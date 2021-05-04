package org.tain.tasks.sendresult;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.queue.ObjectQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component("SendResultTask")
@Slf4j
public class SendResultTask {

	private Session session;
	
	@Bean
	public void startSendResultTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of SendResultTask.");
		
		if (!Flag.flag) {
			/*
			for (int i=0; ; i++) {
				try {
					WebSocketClient webSocketClient = new WebSocketClient();
					WebSocketContainer container = ContainerProvider.getWebSocketContainer();
					this.session = container.connectToServer(webSocketClient, URI.create("ws://localhost:8093/v0.1/websocket"));
					break;
				} catch (Exception e) {
					//e.printStackTrace();
					System.out.println(">>>>> connection failed. -> " + e.getMessage());
				}
				System.out.println(">>>>> try to connect again....." + i);
				Sleep.run(10 * 1000);
			}
			
			System.out.println(">>>>> Start MonWebSocketClient.....");
			*/
		}
	}
	
	public void recvMessage(String message) throws Exception {
		System.out.println("[recvMessage] message: " + message);
	}
	
	public void sendMessage(String message) throws Exception {
		System.out.println("[sendMessage] message: " + message);
		this.session.getAsyncRemote().sendText(message);
	}
	
	public void close() throws Exception {
		this.session.close();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private ObjectQueue queueSendResult = new ObjectQueue();
	
	public void setQueueSendResult(Object object) {
		this.queueSendResult.set(object);
	}
	
	public Object getQueueSendResult() {
		return this.queueSendResult.get();
	}
	
	// sendToMonster
	@Async(value = "async_0102")
	public void async0102(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0102 START {} {}", param, CurrentInfo.get());
		
		if (Flag.flag) {
			Sleep.run(5 * 1000);
			for (int i=0; ; i++) {
				try {
					WebSocketClient webSocketClient = new WebSocketClient();
					WebSocketContainer container = ContainerProvider.getWebSocketContainer();
					this.session = container.connectToServer(webSocketClient, URI.create("ws://localhost:8093/v0.1/websocket"));
					break;
				} catch (Exception e) {
					//e.printStackTrace();
					System.out.println(">>>>> connection failed. -> " + e.getMessage());
				}
				System.out.println(">>>>> try to connect again....." + i);
				Sleep.run(10 * 1000);
			}
			
			System.out.println(">>>>> Start MonWebSocketClient.....");
		}
		
		if (Flag.flag) {
			while (true) {
				// get result from the queueSendResult
				String msg = (String) this.getQueueSendResult();
				System.out.println(">>>>> 2. async " + param + ": " + msg);
				
				// send result to the monitor
				this.sendMessage(msg);
			}
		}
	}
}
