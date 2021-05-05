package org.tain.tasks.splitCommands;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component("SplitCommandsTask")
@Slf4j
public class SplitCommandsTask {

	@Bean
	public void startSplitCommandsTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of SplitCommandsTask.");
		
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
	
	// SplitCommandsTask
	@Async(value = "async_0103")
	public void async0103(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0103 START {} {}", param, CurrentInfo.get());
	
		if (Flag.flag) {
			while (true) {
				// get resNode
				MonJsonNode resNode = this.getQueue();
				System.out.println(">>>>> 3. async " + param + " resNode: " + resNode.toPrettyString());
				
				// split commands
				
				
				
				/*
				// clone copy reqNode to resNode
				MonJsonNode resNode = null;
				try {
					resNode = reqNode.clone();
				} catch (CloneNotSupportedException e) {
					throw e;
				}
				resNode.put("status", "SUCCESS");
				System.out.println(">>>>> 3. async " + param + " resNode: " + resNode.toPrettyString());
				
				// send resNode to the client
				//WebSocketServerController.broadCast(resNode.toString());
				*/
			}
		}
	}
}
