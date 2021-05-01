package org.tain.tasks;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Task01 {

	private int index = 0;
	
	//@Async(value = "async_Task01_Job01")
	@Async
	public void asyncTask01Job01(String param) throws Exception {
		log.info("KANG-20200721 >>>>> START {} {} {}", param, index++, CurrentInfo.get());
	
		if (Flag.flag) {
			
		}
		
		Sleep.run(5000);
		
		log.info("KANG-20200721 >>>>> END   {} {} {}", param, index++, CurrentInfo.get());
	}
}
