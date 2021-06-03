package org.tain.tasks.asyncCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.data.Cmd;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueueBox;
import org.tain.utils.CurrentInfo;
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
		
		if (Boolean.TRUE) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private MonQueueBox monQueueBox;
	
	private boolean flgKeep = true;
	
	public void stopAsync() {
		this.flgKeep = false;
	}
	
	// AsyncCommandTask
	@Async(value = "async_0101")
	public void async0103(Cmd cmd) throws Exception {
		log.info("KANG-20200721 >>>>> async_0101 START cmd:{} {}", cmd, CurrentInfo.get());
		
		if (!Boolean.TRUE) {
			/*
			switch (cmd.getCmdType().toUpperCase()) {
			case "CMD_NORMAL":
				cmdAgainSingle(cmd);
				break;
			case "CMD_SEQUENCE":
				cmdKeepSingle(cmd);
				break;
			default:
				throw new Exception("KANG ERROR: wrong cmd type [" + cmd.getCmdType() + "]");
			}
			*/
		}
		
		if (Boolean.TRUE) {
			this.flgKeep = true;
			if (Integer.parseInt(cmd.getCmdPeriod()) == 0) {
				cmdKeepSingle(cmd);
			} else {
				cmdAgainSingle(cmd);
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void cmdAgainSingle(Cmd cmd) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			// spring async kill thread
			for (int idx=0; this.flgKeep; idx++) {
				log.info(">>>>> cmd: {} {}", cmd, idx);
				MonJsonNode nodeResult = new MonJsonNode("{}");
				if (Boolean.TRUE) {
					//nodeResult.put("svrCode", cmd.getSvrCode());
					nodeResult.put("msgCode", "CMD_RET");
					//nodeResult.put("cmdCode", cmd.getCmdCode());
					//nodeResult.put("cmdName", cmd.getCmdName());
					//nodeResult.put("cmdDesc", cmd.getCmdDesc());
					nodeResult.put("cmdPeriod", cmd.getCmdPeriod());
					//nodeResult.put("cmdType", cmd.getCmdType());
					nodeResult.put("cmdArr", cmd.getCmdArr());
					//nodeResult.put("cmdDttm", LocalDateTime.now());
				}
				
				StringBuffer sb = null;
				String line = null;
				Process process = null;
				if (Boolean.TRUE) {
					// run process and get the result
					sb = new StringBuffer();
					process = Runtime.getRuntime().exec(cmd.getCmdArr());
				}
				
				if (Boolean.TRUE) {
					BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));  // OUTPUT ?EUC-KR
					while ((line = br.readLine()) != null) {
						sb.append(line).append("\n");
					}
				}
				
				if (Boolean.TRUE) {
					BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));  // ERROR ?EUC-KR
					while ((line = br.readLine()) != null) {
						sb.append(line).append("\n");
					}
				}
				
				if (Boolean.TRUE) {
					int exitVal = process.waitFor();
					int len = sb.length();
					process.destroy();
					
					nodeResult.put("cmdResult", sb.toString());
				}
				
				if (Boolean.TRUE) {
					this.monQueueBox.setQueueSendResult(nodeResult);
				}
				
				// wait for period
				Sleep.run(Integer.parseInt(cmd.getCmdPeriod()) * 1000);
			}
		}
		
		if (Boolean.TRUE) {
			System.out.println("+---------------------------------------------+");
			System.out.println("|                                             |");
			System.out.println("|       Stop of the Async in cmdAgainSingle   |");
			System.out.println("|                                             |");
			System.out.println("+---------------------------------------------+");
		}
	}
	
	private void cmdKeepSingle(Cmd cmd) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			// spring async kill thread
			log.info(">>>>> cmd: {} {}", cmd);
			MonJsonNode nodeResult = new MonJsonNode("{}");
			if (Boolean.TRUE) {
				//nodeResult.put("svrCode", cmd.getSvrCode());
				nodeResult.put("msgCode", "CMD_RET");
				//nodeResult.put("cmdCode", cmd.getCmdCode());
				//nodeResult.put("cmdName", cmd.getCmdName());
				//nodeResult.put("cmdDesc", cmd.getCmdDesc());
				nodeResult.put("cmdPeriod", cmd.getCmdPeriod());
				//nodeResult.put("cmdType", cmd.getCmdType());
				nodeResult.put("cmdArr", cmd.getCmdArr());
				//nodeResult.put("cmdDttm", LocalDateTime.now());
			}
			
			if (Boolean.TRUE) {
				// run process and get the result
				Process process = Runtime.getRuntime().exec(cmd.getCmdArr());
				
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
				String line = null;
				while ((line = br.readLine()) != null && this.flgKeep) {
					nodeResult.put("cmdResult", line + "\n");
					if (Boolean.TRUE) {
						this.monQueueBox.setQueueSendResult(nodeResult);
					}
				}
				
				if (Boolean.TRUE) {
					System.out.println("+---------------------------------------------+");
					System.out.println("|                                             |");
					System.out.println("|       Stop of the Async in cmdKeepSingle    |");
					System.out.println("|                                             |");
					System.out.println("+---------------------------------------------+");
				}
				
				process.waitFor();
				process.destroy();
			}
		}
	}
}
