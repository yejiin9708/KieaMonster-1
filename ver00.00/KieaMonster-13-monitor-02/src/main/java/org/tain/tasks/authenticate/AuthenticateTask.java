package org.tain.tasks.authenticate;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.controller.WebSocketServerController;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueue;
import org.tain.utils.CurrentInfo;
import org.tain.vo.SessionInfo;

import lombok.extern.slf4j.Slf4j;

@Component("AuthenticateTask")
@Slf4j
public class AuthenticateTask {

	@Bean
	public void startAuthenticateTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of AuthenticateTask.");
		
		if (Boolean.TRUE) {
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
	
	// AuthenticateTask
	@Async(value = "async_0104")
	public void async0104(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0104 START {} {}", param, CurrentInfo.get());
	
		if (Boolean.TRUE) {
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
				System.out.println(">>>>> 5. AuthenticateTask " + param + " resNode: " + resNode.toPrettyString());
				
				// send resNode to the client
				String sessionId = resNode.getText("sessionId");
				SessionInfo sessionInfo = WebSocketServerController.findSessionById(sessionId);
				sessionInfo.setUserId  (reqNode.getText("userId"));
				sessionInfo.setGroupCd (reqNode.getText("groupCd"));
				sessionInfo.setRollCd  (reqNode.getText("rollCd"));
				sessionInfo.setSections(reqNode.getText("sections"));
				System.out.println(">>>>> 5-1. AuthenticateTask sessionInfo: " + sessionInfo);
				
				// the same of the above
				//sessionInfo = WebSocketServerController.findSessionById(sessionId);
				//System.out.println(">>>>> 5-2. AuthenticateTask sessionInfo: " + sessionInfo);
				
				WebSocketServerController.sendMessage(sessionId, resNode.toString());
			}
		}
	}
}
