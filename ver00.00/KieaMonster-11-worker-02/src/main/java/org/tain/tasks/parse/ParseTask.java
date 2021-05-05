package org.tain.tasks.parse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.tasks.splitCommands.SplitCommandsTask;
import org.tain.tools.node.MonJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component("ParseTask")
@Slf4j
public class ParseTask {

	@Autowired
	private SplitCommandsTask splitCommandsTask;
	
	@Bean
	public void startParseTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of ParseTask.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void parsing(String reqMessage) {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			MonJsonNode reqNode = null;
			try {
				reqNode = new MonJsonNode(reqMessage);
				log.info("KANG-20210405 >>>>> {} reqNode = {}", CurrentInfo.get(), reqNode.toPrettyString());
				
				String msgCode = reqNode.getText("msgCode");
				switch (msgCode) {
				case "GET_CMDS":
					// transfer to SplitCommandsTask
					this.splitCommandsTask.setQueue(reqNode);
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