package org.tain.tasks.sendresult;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.queue.ObjectQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component("SendResultTask")
@Slf4j
public class SendResultTask {

	@Bean
	public void startSendResultTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of SendResultTask.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private ObjectQueue queueSendResult = new ObjectQueue();
	
	public void setQueueSendResult(Object object) {
		this.queueSendResult.set(object);
	}
	
	public Object getQueueSendResult() {
		return this.queueSendResult.get();
	}
	
	// sendToMonster
	@Async(value = "async_0102")
	public void async0102(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0102 START {} {}", param, CurrentInfo.get());
		
		if (Flag.flag) {
			while (true) {
				// get result from the queueSendResult
				String msg = (String) this.getQueueSendResult();
				System.out.println(">>>>> 2. async " + param + ": " + msg);
				
				// send result to the monitor
			}
		}
	}
}
