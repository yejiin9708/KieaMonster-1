package org.tain.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.tasks.sendResult.SendResultTask;
import org.tain.tasks.splitCommands.SplitCommandsTask;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Tasks {

	/////////////////////////////////////////////////////////////
	
	@Autowired
	private SendResultTask sendResultTask;
	
	@Autowired
	private SplitCommandsTask splitCommandsTask;
	
	@Bean
	//@DependsOn(value = {""})
	public void startTasks() throws Exception {
		log.info("KANG-20210405 >>>>> START {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.sendResultTask.async0102("TASK-0102");
		
		if (Boolean.TRUE) this.splitCommandsTask.async0103("TASK-0103");
		
		log.info("KANG-20210405 >>>>> END   {} {}", CurrentInfo.get());
	}
}
