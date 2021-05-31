package org.tain.tasks.parse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.tain.data.SessionInfo;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueueBox;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component("ParseTask")
@DependsOn({"MonQueueBox"})
@Slf4j
public class ParseTask {

	@Bean
	public void startParseTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of ParseTask.");
		
		if (Boolean.TRUE) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private MonQueueBox monQueueBox;
	
	public void parsing(SessionInfo sessionInfo, String reqMessage) {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			MonJsonNode reqNode = null;
			try {
				reqNode = new MonJsonNode(reqMessage);
				reqNode.put("sessionId", sessionInfo.getSession().getId());
				//log.info("KANG-20210405 >>>>> {} reqNode = {}", CurrentInfo.get(), reqNode.toPrettyString());
				
				String msgCode = reqNode.getText("msgCode");
				switch (msgCode) {
				case "CMD_AUTH":
					// transfer to AuthenticateTask
					this.monQueueBox.setQueueAuthenticate(reqNode);
					break;
				case "CMD_RET":
					// transfer to CurrentTask
					this.monQueueBox.setQueueCurrent(reqNode);
					break;
				case "CMD_HIST":
					// transfer to HistoryTask
					this.monQueueBox.setQueueHistory(reqNode);
					break;
				default:
					throw new Exception("ERROR: couldn't parse the msgCode [" + msgCode + "]");
					//break;
				}
			} catch (Exception e) {
				//e.printStackTrace();
				log.error("KANG-20210405 >>>>> error message: {} at {}", e.getMessage(), CurrentInfo.get());
			}
		}
	}
}
