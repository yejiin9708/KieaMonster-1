package org.tain.working.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AsyncWorking {

	@Autowired
	private Task01 task01;
	
	public void test01() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			for (int i=0; i < 5; i++) {
				String param = "ASYNC_TASK01_JOB01-" + String.valueOf(i);
				this.task01.asyncTask01Job01(param);
				
				Sleep.run(1000);
			}
		}
	}
}
