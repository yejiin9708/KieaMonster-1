package org.tain.working.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.controller.WebSocketController;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WebSocketClientWorking {

	@Autowired
	private WebSocketController webSocketController;
	
	public void test00() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			for (int i=0;; i++) {
				this.webSocketController.broadCast("Hello, world!!! - " + i);
				Sleep.run(5 * 1000);
			}
		}
	}
}
