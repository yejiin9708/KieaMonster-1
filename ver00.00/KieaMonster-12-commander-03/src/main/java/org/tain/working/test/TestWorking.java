package org.tain.working.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueueBox;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestWorking {

	@Autowired
	private MonQueueBox monQueueBox;
	
	public void test00() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			Sleep.run(3 * 1000);
			for (int i=0; i < 3; i++) {
				MonJsonNode node = new MonJsonNode("{}");
				node.put("status", "SUCCESS");
				this.monQueueBox.setQueueRecvResult(node);
				Sleep.run(2 * 1000);
			}
		}
	}
}