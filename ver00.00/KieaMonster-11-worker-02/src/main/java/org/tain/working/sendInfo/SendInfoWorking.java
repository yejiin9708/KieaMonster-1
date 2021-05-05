package org.tain.working.sendInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.tasks.sendResult.SendResultTask;
import org.tain.tools.node.MonJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SendInfoWorking {

	@Autowired
	private SendResultTask sendResultTask;
	
	public void sendInfoMessage() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// send info to the commander
			MonJsonNode infoNode = new MonJsonNode("{}");
			infoNode.put("svrCode", "TEST01");
			infoNode.put("msgCode", "GET_CMDS");
			System.out.println(">>>>> infoNode: " + infoNode.toPrettyString());
			
			// send result
			this.sendResultTask.setQueue(infoNode);
		}
	}
}
