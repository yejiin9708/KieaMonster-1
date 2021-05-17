package org.tain.working.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.tasks.recvResult.RecvResultTask;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestWorking {

	@Autowired
	private RecvResultTask recvResultTask;
	
	public void test00() {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			Sleep.run(3 * 1000);
			for (int i=0; i < 3; i++) {
				String msg = String.format("message is the number %03d.", i);
				this.recvResultTask.setQueue(msg);
				Sleep.run(2 * 1000);
			}
		}
	}
}