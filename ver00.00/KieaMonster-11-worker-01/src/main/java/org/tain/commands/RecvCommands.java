package org.tain.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.data.Cmd;
import org.tain.node.MonJsonNode;
import org.tain.tasks.cmd.TaskCommand;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RecvCommands {

	@Autowired
	private TaskCommand taskCommand;
	
	private Map<String, Cmd> mapCmds = new HashMap<>();
	
	public void convertToMap(MonJsonNode node) {
		log.info("KANG-20210412 >>>>> {} {}", CurrentInfo.get());
		
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
					this.taskCommand.asyncTask01Command01(entry.getKey());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Cmd getCmd(String key) {
		return this.mapCmds.get(key);
	}
}
