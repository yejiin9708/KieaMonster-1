package org.tain.tasks.history;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.controller.WebSocketServerController;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component("HistoryTask")
@Slf4j
public class HistoryTask {

	@Bean
	public void startHistoryTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of HistoryTask.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private MonQueue<MonJsonNode> queueHistory = new MonQueue<>();
	
	public void setQueueHistory(MonJsonNode object) {
		this.queueHistory.set(object);
	}
	
	public MonJsonNode getQueueHistory() {
		return this.queueHistory.get();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	//@Autowired
	//private WebSocketServerController webSocketController;
	
	// HistoryTask
	@Async(value = "async_0102")
	public void async0102(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0102 START {} {}", param, CurrentInfo.get());
	
		if (Flag.flag) {
			while (true) {
				// get result from the queueLoadResult
				MonJsonNode reqNode = this.getQueueHistory();
				
				// clone copy reqNode to resNode
				MonJsonNode resNode = null;
				try {
					resNode = reqNode.clone();
				} catch (CloneNotSupportedException e) {
					throw e;
				}
				resNode.put("status", "SUCCESS");
				System.out.println(">>>>> 2. async " + param + " resNode: " + reqNode.toPrettyString());
				
				// send resNode to the client
				String sessionId = resNode.getText("sessionId");
				WebSocketServerController.sendMessage(sessionId, resNode.toString());
			}
		}
	}
}
