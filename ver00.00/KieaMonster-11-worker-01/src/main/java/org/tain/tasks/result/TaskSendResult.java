package org.tain.tasks.result;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.node.MonJsonNode;
import org.tain.queue.ResultQueue;
import org.tain.tasks.ws.TaskSimpleClient;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskSendResult {

	//@Autowired
	//private TaskSimpleClient taskSimpleClient;
	
	private final ResultQueue resultQueue = new ResultQueue();
	
	public void setQueue(MonJsonNode node) {
		//log.info("KANG-20210412 >>>>> {} {}", CurrentInfo.get(), node.toPrettyString());
		this.resultQueue.set(node);
	}
	
	@Async("async_Task01_SendResult01")
	public void asyncTask01SendResult01(String param, TaskSimpleClient taskSimpleClient) throws Exception {
		log.info("KANG-20210412 >>>>> {} {}", param, CurrentInfo.get());
		
		if (Flag.flag) {
			for (int i=0; ; i++) {
				MonJsonNode nodeResult = (MonJsonNode) this.resultQueue.get();
				log.info("KANG-20210412 >>>>> JSON: ({}) {}", i, nodeResult.toPrettyString());
				
				String message = nodeResult.toString();
				if (Flag.flag) taskSimpleClient.sendMessage(message);
			}
		}
	}
}
