package org.tain.working.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.commands.SendResult;
import org.tain.httpClient.MonHttpClient;
import org.tain.node.MonJsonNode;
import org.tain.queue.ResultQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ResultWorking {
	
	@Autowired
	private SendResult sendResult;
	private ResultQueue resultQueue;
	
	public void test01() throws Exception {
		log.info("KANG-20210412 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.resultQueue = this.sendResult.getResultQueue();
		}
		
		if (Flag.flag) {
			try {
				String message = null;
				while (true) {
					message = (String) this.resultQueue.get();
					log.info("KANG-20210412 >>>>> message: {}", message);
					
					this.sendMessage(message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private MonHttpClient monHttpClient;
	
	private void sendMessage(String message) throws Exception {
		log.info("KANG-20210412 >>>>> {} {}", CurrentInfo.get());
		
		MonJsonNode nodeCmds = null;
		if (Flag.flag) {
			try {
				// httpClient
				///////////////////////////////////////////////
				// 
				MonJsonNode info = new MonJsonNode("{}");
				if (Flag.flag) info.put("url", "http://localhost:8012/v0.1/rest/result/tbResult");
				
				info.put("method", "POST");
				
				MonJsonNode header = new MonJsonNode("{}");
				header.put("Content-Type", "application/json");
				header.put("header01", "value01");
				
				MonJsonNode request = new MonJsonNode("{}");
				request.put("message", message);
				
				MonJsonNode response = new MonJsonNode("{}");
				
				///////////////////////////////////////////////
				//
				MonJsonNode send = new MonJsonNode("{}");
				send.put("_info", info);
				send.put("_header", header);
				send.put("_request", request);
				send.put("_response", response);
				log.info("KANG-20210412 >>>>> send node: " + send.toPrettyString());
				
				///////////////////////////////////////////////
				//
				log.info("KANG-20210412 >>>>> info     node: " + send.getMonJsonNode("_info"    ).toPrettyString());
				log.info("KANG-20210412 >>>>> info     node: " + send.getMap("_info"));
				log.info("KANG-20210412 >>>>> header   node: " + send.getMonJsonNode("_header"  ).toPrettyString());
				log.info("KANG-20210412 >>>>> request  node: " + send.getMonJsonNode("_request" ).toPrettyString());
				log.info("KANG-20210412 >>>>> response node: " + send.getMonJsonNode("_response").toPrettyString());
				
				///////////////////////////////////////////////
				//
				MonJsonNode recv = this.monHttpClient.execute(send);
				log.info("KANG-20210412 >>>>> recv node: " + recv.toPrettyString());
				
				nodeCmds = recv.getMonJsonNode("_response");
				log.info("KANG-20210412 >>>>> nodeCmds node: " + nodeCmds.toPrettyString());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}
	}
}
