package org.tain.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.tasks.sendresult.SendResultTask;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Tasks {

	/////////////////////////////////////////////////////////////
	
	@Autowired
	private SendResultTask sendResultTask;
	
	@Bean
	public void startTask() throws Exception {
		log.info("KANG-20210405 >>>>> START {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.sendResultTask.async0102("TASK-0102");
		
		log.info("KANG-20210405 >>>>> END   {} {}", CurrentInfo.get());
	}
}
