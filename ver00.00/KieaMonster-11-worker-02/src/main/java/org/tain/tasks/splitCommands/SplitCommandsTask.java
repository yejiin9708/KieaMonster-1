package org.tain.tasks.splitCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.data.Cmd;
import org.tain.tasks.asyncCommand.AsyncCommandTask;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component("SplitCommandsTask")
@Slf4j
public class SplitCommandsTask {

	@Bean
	public void startSplitCommandsTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of SplitCommandsTask.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private MonQueue<MonJsonNode> queue = new MonQueue<>();
	
	public void setQueue(MonJsonNode object) {
		this.queue.set(object);
	}
	
	public MonJsonNode getQueue() {
		return this.queue.get();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private Map<String, Cmd> mapCmds = new HashMap<>();
	
	public Cmd getCmd(String key) {
		return this.mapCmds.get(key);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private AsyncCommandTask asyncCommandTask;
	
	// SplitCommandsTask
	@Async(value = "async_0103")
	public void async0103(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0103 START {} {}", param, CurrentInfo.get());
	
		if (Flag.flag) {
			while (true) {
				// get resNode
				MonJsonNode node = this.getQueue();
				System.out.println(">>>>> 3. async " + param + " resNode: " + node.toPrettyString());
				
				if (Flag.flag) {
					// load to mapCmds
					try {
						List<Cmd> lstCmds = new ObjectMapper().readValue(node.getArrayNode("resResult").toString(), new TypeReference<List<Cmd>>(){});
						for (Cmd cmd : lstCmds) {
							System.out.println(">>>>> " + cmd);
							String key = String.valueOf(cmd.getId());
							this.mapCmds.put(key, cmd);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if (Flag.flag) {
					// create async cmds
					try {
						for (Map.Entry<String, Cmd> entry : this.mapCmds.entrySet()) {
							this.asyncCommandTask.async0103(entry.getKey());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
				/*
				// clone copy reqNode to resNode
				MonJsonNode resNode = null;
				try {
					resNode = reqNode.clone();
				} catch (CloneNotSupportedException e) {
					throw e;
				}
				resNode.put("status", "SUCCESS");
				System.out.println(">>>>> 3. async " + param + " resNode: " + resNode.toPrettyString());
				
				// send resNode to the client
				//WebSocketServerController.broadCast(resNode.toString());
				*/
			}
		}
	}
}
