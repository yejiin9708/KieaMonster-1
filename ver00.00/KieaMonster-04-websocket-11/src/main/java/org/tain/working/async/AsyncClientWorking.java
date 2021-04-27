package org.tain.working.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.tasks.ws.TaskSimpleClient;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AsyncClientWorking {

	@Autowired
	private TaskSimpleClient taskSimpleClient;
	
	public void test00() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.taskSimpleClient.asyncTask01Job01("ASYNC_TASK01_JOB01");
		}
	}
	
	public void test01() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			for (int i=0; ; i++) {
				String message = String.format("message number = %04d", i);
				this.taskSimpleClient.sendMessage(message);
				Sleep.run(10 * 1000);
			}
		}
	}
}
