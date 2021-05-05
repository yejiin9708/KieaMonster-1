package org.tain.tasks.asyncCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.data.Cmd;
import org.tain.tasks.sendResult.SendResultTask;
import org.tain.tasks.splitCommands.SplitCommandsTask;
import org.tain.tools.node.MonJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component("SplitCommandsTask")
@Slf4j
public class AsyncCommandTask {

	@Bean
	public void startAsyncCommandTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of AsyncCommandTask.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private SplitCommandsTask splitCommandsTask;
	
	@Autowired
	private SendResultTask sendResultTask;
	
	// AsyncCommandTask
	@Async(value = "async_0101")
	public void async0103(String key) throws Exception {
		log.info("KANG-20200721 >>>>> async_0101 START {} {}", key, CurrentInfo.get());
	
		Cmd cmd = null;
		if (Flag.flag) {
			cmd = this.splitCommandsTask.getCmd(key);
			log.info(">>>>> cmd-{}: {}", key, cmd);
		}
		
		if (Flag.flag) {
			for (int i=0; ; i++) {
				log.info(">>>>> cmd-{}: {} {}", key, cmd, i);
				MonJsonNode nodeResult = new MonJsonNode("{}");
				if (Flag.flag) {
					nodeResult.put("svrCode", cmd.getSvrCode());
					nodeResult.put("msgCode", "CMD_RET");
					nodeResult.put("cmdCode", cmd.getCmdCode());
					nodeResult.put("cmdName", cmd.getCmdName());
					nodeResult.put("cmdDesc", cmd.getCmdDesc());
					nodeResult.put("cmdArr", cmd.getCmdArr());
					nodeResult.put("cmdPeriod", cmd.getCmdPeriod());
					//nodeResult.put("cmdDttm", LocalDateTime.now());
				}
				
				if (Flag.flag) {
					Process process = Runtime.getRuntime().exec(cmd.getCmdArr());
					
					BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
					StringBuffer sb = new StringBuffer();
					String line = null;
					//System.out.println("<OUTPUT>");
					//sb.append("<OUTPUT>").append("\n");
					while ((line = br.readLine()) != null) {
						//System.out.println("> " + line);
						//sb.append("> ");
						sb.append(line).append("\n");
					}
					//System.out.println("</OUTPUT>");
					//sb.append("</OUTPUT>").append("\n");
					
					int exitVal = process.waitFor();
					//System.out.println("Process exitVal = " + exitVal);
					sb.append(String.format("(%d) Process exitValue = %d", i, exitVal)).append("\n\n");
					process.destroy();
					
					//System.out.println(sb.toString());
					nodeResult.put("cmdResult", sb.toString());
				}
				
				if (Flag.flag) {
					this.sendResultTask.setQueue(nodeResult);
				}
				
				// wait for period
				Sleep.run(Integer.parseInt(cmd.getCmdPeriod()) * 1000);
			}
		}
	}
}
