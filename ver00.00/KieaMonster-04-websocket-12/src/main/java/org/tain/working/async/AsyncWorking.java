package org.tain.working.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.task.TaskSimpleServer;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AsyncWorking {

	@Autowired
	private TaskSimpleServer taskSimpleServer;
	
	///////////////////////////////////////////////////////////////////////////////
	//
	public void test01() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			String param = "ASYNC_TASK01_JOB01";
			this.taskSimpleServer.asyncTask01Job01(param);  // Async thread
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////
	//
	public void test02() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			String msg = "##### Hello Kiea #####";
			this.taskSimpleServer.sendMessage(msg);  // send to all client from server
		}
	}
}
