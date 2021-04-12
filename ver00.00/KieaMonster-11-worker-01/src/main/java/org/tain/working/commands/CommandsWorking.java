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
			this.svrCode = this.projEnvBaseProperties.getSvrCode();
		}
		
		if (Flag.flag) {
			// httpClient
			MonJsonNode nodeCmds = this.asyncCommands.jobHttpClient(svrCode);
			log.info("KANG-20210412 >>>>> nodeCmds node: " + nodeCmds.toPrettyString());
		}
		
		if (Flag.flag) {
			// to List<Cmd>
			List<Cmd> lstCmds = this.asyncCommands.jobToListCmds();
			lstCmds.forEach(cmd -> {
				log.info("KANG-20210412 >>>>> cmd = {}", cmd);
			});
		}
		
		if (Flag.flag) {
			// cmds to async
			int asyncSize = this.asyncCommands.jobToAsyncCommands();
			log.info("KANG-20210412 >>>>> asyncSize: " + asyncSize);
		}
	}
}
