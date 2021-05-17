package org.tain.tasks.splitCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.tasks.asyncCommand.AsyncCommandTask;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueueBox;
import org.tain.utils.CurrentInfo;
import org.tain.vo.Cmd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component("SplitCommandsTask")
@Slf4j
@DependsOn({"MonQueueBox"})
//@Lazy
public class SplitCommandsTask {

	@Bean
	public void startSplitCommandsTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of SplitCommandsTask.");
		
		if (Boolean.TRUE) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private MonQueueBox monQueueBox;
	
	@Autowired
	private AsyncCommandTask asyncCommandTask;
	
	// SplitCommandsTask
	@Async(value = "async_0103")
	public void async0103(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0103 START {} {}", param, CurrentInfo.get());
	
		if (Boolean.TRUE) {
			Map<String, Cmd> mapCmds = new HashMap<>();
			while (true) {
				// get resNode
				mapCmds.clear();
				MonJsonNode node = this.monQueueBox.getQueueSplitCommands();
				System.out.println(">>>>> 3. async " + param + " resNode: " + node.toPrettyString());
				
				if (Boolean.TRUE) {
					// load to mapCmds
					try {
						List<Cmd> lstCmds = new ObjectMapper().readValue(node.getArrayNode("resResult").toString(), new TypeReference<List<Cmd>>(){});
						for (Cmd cmd : lstCmds) {
							System.out.println(">>>>> " + cmd);
							String key = String.valueOf(cmd.getId());
							mapCmds.put(key, cmd);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if (Boolean.TRUE) {
					// create async cmds
					try {
						for (Map.Entry<String, Cmd> entry : mapCmds.entrySet()) {
							//this.asyncCommandTask.async0103(entry.getKey());
							this.asyncCommandTask.async0103(entry.getValue());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			}
		}
	}
}
