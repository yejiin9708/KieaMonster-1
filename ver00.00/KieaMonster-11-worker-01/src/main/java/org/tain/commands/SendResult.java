package org.tain.commands;

import org.springframework.stereotype.Component;
import org.tain.queue.ResultQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SendResult {

	private final ResultQueue resultQueue = new ResultQueue();
	
	public ResultQueue getResultQueue() {
		return this.resultQueue;
	}
	
	@Deprecated
	public void test01() throws Exception {
		log.info("KANG-20210412 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			
		}
	}
}
