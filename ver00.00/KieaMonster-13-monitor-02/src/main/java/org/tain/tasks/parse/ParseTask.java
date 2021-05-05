package org.tain.tasks.parse;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.tasks.current.CurrentTask;
import org.tain.tasks.history.HistoryTask;
import org.tain.tools.node.MonJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component("ParseTask")
@Slf4j
public class ParseTask {

	@Autowired
	private CurrentTask currentTask;
	
	@Autowired
	private HistoryTask historyTask;
	
	@Bean
	public void startParseTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of ParseTask.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void parsing(Session session, String reqMessage) {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			MonJsonNode reqNode = null;
			try {
				reqNode = new MonJsonNode(reqMessage);
				reqNode.put("sessionId", session.getId());
				log.info("KANG-20210405 >>>>> {} reqNode = {}", CurrentInfo.get(), reqNode.toPrettyString());
				
				String msgCode = reqNode.getText("msgCode");
				switch (msgCode) {
				case "CMD_RET":
					// transfer to CurrentTask
					this.currentTask.setQueueCurrent(reqNode);
					break;
				case "CMD_HIST":
					// transfer to HistoryTask
					this.historyTask.setQueueHistory(reqNode);
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
