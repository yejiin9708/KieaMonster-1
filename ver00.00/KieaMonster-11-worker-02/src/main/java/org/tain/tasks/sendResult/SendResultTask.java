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
import org.tain.tasks.parse.WebSocketClient;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.properties.ProjEnvUrlProperties;
import org.tain.tools.queue.MonQueueBox;
import org.tain.utils.CurrentInfo;

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
		
		Session session = null;
		if (Boolean.TRUE) {
			// create a connection with websocket
			try {
				WebSocketClient webSocketClient = new WebSocketClient(this.parseTask);
				WebSocketContainer container = ContainerProvider.getWebSocketContainer();
				String wsUri = this.projEnvUrlProperties.getWsUri();
				session = container.connectToServer(webSocketClient, URI.create(wsUri));
			} catch (Exception e) {
				System.exit(0);
				throw e;
			}
			System.out.println(">>>>> Start WebSocketClient.....");
		}
		
		if (Boolean.TRUE) {
			// send results to the commander
			try {
				while (true) {
					// get result from the queueSendResult
					MonJsonNode resultNode = this.monQueueBox.getQueueSendResult();
					System.out.println(">>>>> 2. async " + param + ": " + resultNode.toPrettyString());
					
					// send result
					session.getAsyncRemote().sendText(resultNode.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		// retry to connect
	}
}
