package org.tain.tasks.current;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.controller.WebSocketServerController;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueueBox;
import org.tain.utils.CurrentInfo;
import org.tain.vo.SessionInfo;

import lombok.extern.slf4j.Slf4j;

@Component("CurrentTask")
@DependsOn({"MonQueueBox"})
@Slf4j
public class CurrentTask {

	@Bean
	public void startCurrentTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of CurrentTask.");
		
		if (Boolean.TRUE) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	private MonQueue<MonJsonNode> queue = new MonQueue<>();
	
	public void setQueue(MonJsonNode object) {
		this.queue.set(object);
	}
	
	public MonJsonNode getQueue() {
		return this.queue.get();
	}
	*/
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private MonQueueBox monQueueBox;
	
	// CurrentTask
	@Async(value = "async_0101")
	public void async0101(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0101 START {} {}", param, CurrentInfo.get());
	
		if (Boolean.TRUE) {
			while (true) {
				// get reqNode
				MonJsonNode reqNode = this.monQueueBox.getQueueAuthenticate();
				
				// clone copy reqNode to resNode
				MonJsonNode resNode = null;
				try {
					resNode = reqNode.clone();
				} catch (CloneNotSupportedException e) {
					throw e;
				}
				resNode.put("status", "SUCCESS");
				System.out.println(">>>>> 1. async " + param + " resNode: " + resNode.toPrettyString());
				
				// send resNode to the client
				//WebSocketServerController.broadCast(resNode.toString());
				String code = reqNode.getText("svrCode") + "-" + reqNode.getText("cmdCode");
				for (Map.Entry<String, SessionInfo> entry : WebSocketServerController.peers.entrySet()) {
					String sections = entry.getValue().getSections();
					if (sections != null && code.equals(sections)) {
						WebSocketServerController.sendMessage(entry.getValue().getSession(), resNode.toString());
					}
				}
			}
		}
	}
}
