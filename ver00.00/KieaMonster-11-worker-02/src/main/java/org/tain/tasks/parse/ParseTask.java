package org.tain.tasks.parse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueueBox;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component("parseTask")
@Slf4j
@DependsOn({"MonQueueBox"})
//@Lazy
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
	
	public void parsing(String message) {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			MonJsonNode node = null;
			try {
				node = new MonJsonNode(message);
				log.info("KANG-20210405 >>>>> {} reqNode = {}", CurrentInfo.get(), node.toPrettyString());
				
				String msgCode = node.getText("msgCode");
				switch (msgCode) {
				case "GET_CMDS":
					// transfer to SplitCommandsTask
					this.monQueueBox.setQueueSplitCommands(node);
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
