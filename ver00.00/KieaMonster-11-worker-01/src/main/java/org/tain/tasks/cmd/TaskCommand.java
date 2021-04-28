package org.tain.tasks.cmd;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.commands.RecvCommands;
import org.tain.data.Cmd;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskCommand {

	@Autowired
	private RecvCommands recvCommands;
	
	@Async("async_Task01_Command01")
	public void asyncTask01Command01(String key) throws Exception {
		log.info(">>>>> TaskCommand.asyncTask01Command01: {}", key);
		
		Cmd cmd = null;
		if (Flag.flag) {
			cmd = this.recvCommands.getCmd(key);
			// log.info(">>>>> cmd-{}: {}", key, cmd);
		}
		
		if (Flag.flag) {
			for (int i=0; ; i++) {
				//log.info(">>>>> cmd-{}: {} {}", key, cmd, i);
				if (Flag.flag) {
					Process process = Runtime.getRuntime().exec(cmd.getCmdArr());
					
					BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
					StringBuffer sb = new StringBuffer();
					String line = null;
					//System.out.println("<OUTPUT>");
					sb.append("<OUTPUT>").append("\n");
					while ((line = br.readLine()) != null) {
						//System.out.println("> " + line);
						sb.append("> ").append(line).append("\n");
					}
					//System.out.println("</OUTPUT>");
					sb.append("</OUTPUT>").append("\n");
					int exitVal = process.waitFor();
					//System.out.println("Process exitVal = " + exitVal);
					sb.append(String.format("(%d) Process exitValue = %d", i, exitVal)).append("\n\n");
					process.destroy();
					
					System.out.println(sb.toString());
				}
				
				// period
				Sleep.run(Integer.parseInt(cmd.getCmdPeriod()) * 1000);
			}
		}
	}
}
