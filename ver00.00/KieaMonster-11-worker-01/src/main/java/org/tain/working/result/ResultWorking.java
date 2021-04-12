package org.tain.working.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.commands.SendResult;
import org.tain.queue.ResultQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ResultWorking {
	
	@Autowired
	private SendResult sendResult;
	private ResultQueue resultQueue;
	
	public void test01() throws Exception {
		log.info("KANG-20210412 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.resultQueue = this.sendResult.getResultQueue();
		}
		
		if (Flag.flag) {
			try {
				String message = null;
				while (true) {
					message = (String) this.resultQueue.get();
					
					System.out.println("RESULT >>>>> " + message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}
	}
}
