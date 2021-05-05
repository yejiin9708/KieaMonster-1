package org.tain.tasks.asyncCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.data.Cmd;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueueBox;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component("AsyncCommandTask")
@Slf4j
@DependsOn({"MonQueueBox"})
//@Lazy
public class AsyncCommandTask {

	//@Bean
	public void startAsyncCommandTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of AsyncCommandTask.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private MonQueueBox monQueueBox;
	
	// AsyncCommandTask
	@Async(value = "async_0101")
	public void async0103(Cmd cmd) throws Exception {
		log.info("KANG-20200721 >>>>> async_0101 START cmd:{} {}", cmd, CurrentInfo.get());
	
		if (Flag.flag) {
			// spring async kill thread
			for (int idx=0; ; idx++) {
				log.info(">>>>> cmd: {} {}", cmd, idx);
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
					// run process and get the result
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
					int len = sb.length();
					sb.insert(0, "----------------------------------------------------\n");
					sb.insert(0, String.format("DATE: %s\n", new Date()));
					sb.insert(0, String.format("[idx:%d,len:%d] Process exitValue = %d\n", idx, len, exitVal));
					process.destroy();
					
					//System.out.println(sb.toString());
					nodeResult.put("cmdResult", sb.toString());
				}
				
				if (Flag.flag) {
					this.monQueueBox.setQueueSendResult(nodeResult);
				}
				
				// wait for period
				Sleep.run(Integer.parseInt(cmd.getCmdPeriod()) * 1000);
			}
		}
	}
}
