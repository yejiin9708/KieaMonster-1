package org.tain.tasks.sendResult;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.tasks.parse.ParseTask;
import org.tain.tasks.parse.WebSocketClient;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component("SendResultTask")
@Slf4j
public class SendResultTask {

	@Bean
	public void startSendResultTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of SendResultTask.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	// queue for sending message
	private MonQueue<MonJsonNode> queue = new MonQueue<>();
	
	public void setQueue(MonJsonNode object) {
		this.queue.set(object);
	}
	
	public MonJsonNode getQueue() {
		return this.queue.get();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ParseTask parseTask;
	
	private Session session;
	
	// sendResult
	@Async(value = "async_0102")
	public void async0102(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0102 START {} {}", param, CurrentInfo.get());
		
		if (Flag.flag) {
			// create a connection with websocket
			try {
				WebSocketClient webSocketClient = new WebSocketClient(this.parseTask);
				WebSocketContainer container = ContainerProvider.getWebSocketContainer();
				this.session = container.connectToServer(webSocketClient, URI.create("ws://localhost:8092/v0.1/websocket"));
			} catch (Exception e) {
				throw e;
			}
			System.out.println(">>>>> Start WebSocketClient.....");
		}
		
		if (Flag.flag) {
			// send results to the commander
			try {
				while (true) {
					// get result from the queueSendResult
					MonJsonNode resultNode = this.getQueue();
					System.out.println(">>>>> 2. async " + param + ": " + resultNode.toPrettyString());
					
					// send result
					this.session.getAsyncRemote().sendText(resultNode.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.session.close();
			}
			
			// retry to connect
		}
	}
}
