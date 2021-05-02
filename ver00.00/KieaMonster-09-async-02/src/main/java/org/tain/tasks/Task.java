package org.tain.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.tasks.sample.TaskSample;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Task {

	/////////////////////////////////////////////////////////////
	
	@Autowired
	private TaskSample taskSample;
	
	@Bean
	public void startTask() throws Exception {
		log.info("KANG-20210405 >>>>> START {} {}", CurrentInfo.get());
		
		this.taskSample.async0101("HELLO-0101");
		this.taskSample.async0101("HELLO-0102");
		this.taskSample.async0101("HELLO-0103");
		
		this.taskSample.async0102("HELLO-02");
		
		if (Flag.flag) {
			for (int i=0; i < 100; i++) {
				this.taskSample.setObject(String.format("%03d", i));
				Sleep.run(1 * 1000);
			}
		}
		
		log.info("KANG-20210405 >>>>> END   {} {}", CurrentInfo.get());
	}
}
