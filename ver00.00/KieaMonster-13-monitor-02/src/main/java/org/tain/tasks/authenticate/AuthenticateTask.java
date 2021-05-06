package org.tain.tasks.authenticate;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.controller.WebSocketServerController;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component("AuthenticateTask")
@Slf4j
public class AuthenticateTask {

	@Bean
	public void startAuthenticateTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of AuthenticateTask.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
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
	
	// CurrentTask
	@Async(value = "async_0101")
	public void async0101(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0101 START {} {}", param, CurrentInfo.get());
	
		if (Flag.flag) {
			// 인증이 되지 않은 세션은 종료(close) 한다.
			while (true) {
				// get reqNode
				MonJsonNode reqNode = this.getQueue();
				
				// clone copy reqNode to resNode
				MonJsonNode resNode = null;
				try {
					resNode = reqNode.clone();
				} catch (CloneNotSupportedException e) {
					throw e;
				}
				resNode.put("status", "SUCCESS");
				System.out.println(">>>>> 4. async " + param + " resNode: " + resNode.toPrettyString());
				
				// send resNode to the client
				String sessionId = resNode.getText("sessionId");
				WebSocketServerController.sendMessage(sessionId, resNode.toString());
			}
		}
	}
}