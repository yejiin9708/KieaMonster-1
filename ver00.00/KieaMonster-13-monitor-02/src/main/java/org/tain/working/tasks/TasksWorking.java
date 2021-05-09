package org.tain.working.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TasksWorking {

	@Autowired
	private DemoTask demoTask;
	
	public void runTasks() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.demoTask.async0103("TASK-0103");
	}
}
