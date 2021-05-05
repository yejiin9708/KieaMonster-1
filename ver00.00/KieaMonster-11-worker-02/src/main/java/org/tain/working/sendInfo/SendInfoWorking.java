package org.tain.working.sendInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueueBox;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@DependsOn({"MonQueueBox"})
public class SendInfoWorking {

	@Autowired
	private MonQueueBox monQueueBox;
	
	public void sendInfoMessage() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// send info to the commander
			MonJsonNode infoNode = new MonJsonNode("{}");
			infoNode.put("svrCode", "TEST01");
			infoNode.put("msgCode", "GET_CMDS");
			System.out.println(">>>>> infoNode: " + infoNode.toPrettyString());
			
			// send result
			this.monQueueBox.setQueueSendResult(infoNode);
		}
	}
}
