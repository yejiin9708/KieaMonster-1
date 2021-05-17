package org.tain.tasks.sendResult;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.tasks.parse.ParseTask;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.properties.ProjEnvUrlProperties;
import org.tain.tools.queue.MonQueueBox;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component("SendResultTask")
@Slf4j
@DependsOn({"MonQueueBox"})
public class SendResultTask {

	@Bean
	public void startSendResultTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of SendResultTask.");
		
		if (Boolean.TRUE) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private MonQueueBox monQueueBox;
	
	@Autowired
	private ParseTask parseTask;
	
	// sendResult
	@Async(value = "async_0102")
	public void async0102(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0102 START {} {}", param, CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.connect();
		}
		
		if (Boolean.TRUE) {
			// send results to the commander
			try {
				while (true) {
					// get result from the queueSendResult
					MonJsonNode resultNode = this.monQueueBox.getQueueSendResult();
					System.out.println(">>>>> 2. async " + param + ": " + resultNode.toPrettyString());
					
					// send result
					this.sendMessage(resultNode.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.session.close();
			}
		}
		// retry to connect
	}
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private Session session;
	
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
	
	private void connect() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			Sleep.run(2 * 1000);
			for (int i=0; ; i++) {
				try {
					WebSocketClient webSocketClient = new WebSocketClient(this.parseTask);
					WebSocketContainer container = ContainerProvider.getWebSocketContainer();
					String wsUri = this.projEnvUrlProperties.getWsUri();
					this.session = container.connectToServer(webSocketClient, URI.create(wsUri));
					
					// couldn't clear queue, because of sendInfoMessage
					//this.monQueueBox.clearQueueSendResult();
					break;
				} catch (Exception e) {
					//e.printStackTrace();
					System.out.println(">>>>> connection failed. -> " + e.getMessage());
				}
				System.out.println(">>>>> try to connect again....." + i);
				Sleep.run(10 * 1000);
			}
			
			System.out.println(">>>>> Start MonWebSocketClient.....sessionId: " + this.session.getId());
		}
	}
}