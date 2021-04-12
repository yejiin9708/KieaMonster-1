package org.tain.working.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.commands.AsyncCommands;
import org.tain.data.Cmd;
import org.tain.node.MonJsonNode;
import org.tain.properties.ProjEnvBaseProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommandsWorking {

	@Autowired
	private ProjEnvBaseProperties projEnvBaseProperties;
	
	@Autowired
	private AsyncCommands asyncCommands;
	
	private String svrCode = null;
	
	public void test01() throws Exception {
		log.info("KANG-20210412 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// get svrCode
			this.svrCode = this.projEnvBaseProperties.getSvrCode();
		}
		
		if (Flag.flag) {
			// init
			this.asyncCommands.jobInit();
		}
		
		if (Flag.flag) {
			// httpClient to get the commands of this server
			MonJsonNode nodeCmds = this.asyncCommands.jobHttpClient(svrCode);
			log.info("KANG-20210412 >>>>> nodeCmds node: " + nodeCmds.toPrettyString());
		}
		
		int asyncSize = 0;
		if (Flag.flag) {
			// to List<Cmd>
			List<Cmd> lstCmds = this.asyncCommands.jobToListCmds();
			lstCmds.forEach(cmd -> {
				log.info("KANG-20210412 >>>>> cmd = {}", cmd);
			});
			asyncSize = lstCmds.size();
		}
		
		if (Flag.flag) {
			/*
			// no async thread
			// cmds to async
			asyncSize = this.asyncCommands.jobToStartAsyncCommands();
			log.info("KANG-20210412 >>>>> asyncSize: " + asyncSize);
			 */
		}
		
		if (Flag.flag) {
			// async thread
			for (int i=0; i < asyncSize; i++) {
				this.asyncCommands.asyncCommand(i);  // run async thread
				
				if (Flag.flag) Sleep.run(1000);
			}
		}
	}
}
