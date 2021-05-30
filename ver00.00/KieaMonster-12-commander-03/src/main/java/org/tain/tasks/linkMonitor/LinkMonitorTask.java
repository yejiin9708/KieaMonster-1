package org.tain.tasks.linkMonitor;

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

@Component("LinkMonitorTask")
@DependsOn({"MonQueueBox"})
@Slf4j
public class LinkMonitorTask {

	@Bean
	public void startLinkMonitorTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of LinkMonitorTask.");
		
		if (!Boolean.TRUE) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private MonQueueBox monQueueBox;
	
	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private ParseTask parseTask;
	
	// sendToMonster
	@Async(value = "async_0102")
	public void async0102(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0102 START {} {}", param, CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.connect();
		}
		
		if (Boolean.TRUE) {
			try {
				while (true) {
					// get result from the queueSendResult
					MonJsonNode node = this.monQueueBox.getQueueLinkMonitor();
					System.out.println(">>>>> 2. async " + param + ": " + node.toPrettyString());
					
					// send result to the monitor
					this.sendMessage(node.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.session.close();
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private Session session;
	
	public void recvMessage(String message) {
		System.out.println("[recvMessage] message: " + message);
		this.parseTask.parsing(this.session, message);
	}
	
	public void sendMessage(String message) throws Exception {
		System.out.println("[sendMessage] message: " + message);
		this.session.getAsyncRemote().sendText(message);
	}
	
	public void close() throws Exception {
		this.session.close();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void connect() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			Sleep.run(2 * 1000);
			for (int i=0; ; i++) {
				WebSocketClient webSocketClient = null;
				try {
					webSocketClient = new WebSocketClient(this);
					WebSocketContainer container = ContainerProvider.getWebSocketContainer();
					String wsUri = this.projEnvUrlProperties.getWsUri();
					this.session = container.connectToServer(webSocketClient, URI.create(wsUri));
					
					// clear queue
					//this.queue.clear();
					break;
				} catch (Exception e) {
					//e.printStackTrace();
					System.out.println(">>>>> connection failed. -> " + e.getMessage());
					webSocketClient.onClose();
				}
				System.out.println(">>>>> try to connect again....." + i);
				Sleep.run(10 * 1000);
			}
			
			System.out.println(">>>>> Start MonWebSocketClient.....sessionId: " + this.session.getId());
		}
	}
}
