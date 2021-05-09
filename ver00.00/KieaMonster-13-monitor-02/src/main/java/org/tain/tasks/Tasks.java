package org.tain.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.tain.tasks.authenticate.AuthenticateTask;
import org.tain.tasks.current.CurrentTask;
import org.tain.tasks.history.HistoryTask;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Tasks {

	/////////////////////////////////////////////////////////////
	
	@Autowired
	private CurrentTask currentTask;
	
	@Autowired
	private HistoryTask historyTask;
	
	@Autowired
	private AuthenticateTask authenticateTask;
	
	@Bean
	@DependsOn(value = {"startWebSocketController"})
	public void startTasks() throws Exception {
		log.info("KANG-20210405 >>>>> START {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.currentTask.async0101("TASK-0101");
		
		if (Boolean.TRUE) this.historyTask.async0102("TASK-0102");
		
		if (Boolean.TRUE) this.authenticateTask.async0104("TASK-0104");
		
		log.info("KANG-20210405 >>>>> END   {} {}", CurrentInfo.get());
	}
}
