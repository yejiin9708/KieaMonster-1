package org.tain.tasks.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.commands.RecvCommands;
import org.tain.data.Cmd;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskCommand {

	@Autowired
	private RecvCommands recvCommands;
	
	@Async("async_Task01_Command01")
	public void asyncTask01Command01(String key) throws Exception {
		log.info(">>>>> TaskCommand.asyncTask01Command01: {}", key);
		
		if (Flag.flag) {
			Cmd cmd = this.recvCommands.getCmd(key);
			log.info(">>>>> cmd-{}: {}", key, cmd);
			
			// TODO: KANG-2021-04-27
		}
	}
}
