package org.tain.working.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.tasks.result.TaskSendResult;
import org.tain.tasks.ws.TaskSimpleClient;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AsyncTaskSendResultWorking {

	@Autowired
 	private TaskSimpleClient taskSimpleClient;
	
	@Autowired
	private TaskSendResult taskSendResult;
	
	public void test00() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.taskSendResult.asyncTask01SendResult01("ASYNC_TASK01_SENDRESULT01", this.taskSimpleClient);
		}
	}
}
