package org.tain.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.data.Cmd;
import org.tain.httpClient.MonHttpClient;
import org.tain.node.MonJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AsyncCommands {

	@Autowired
	private MonHttpClient monHttpClient;
	
	private MonJsonNode nodeCmds = null;
	private List<Cmd> lstCmds = null;
	
	public MonJsonNode jobHttpClient(String svrCode) throws Exception {
		log.info("KANG-20210412 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// httpClient
			///////////////////////////////////////////////
			// 
			MonJsonNode info = new MonJsonNode("{}");
			if (!Flag.flag) info.put("url", "http://localhost:8080/v0.1/rest/worker/tbCmd/TEST01");
			if (!Flag.flag) info.put("url", "http://localhost:8080/v0.1/rest/worker/tbCmd/TEST02");
			if (!Flag.flag) info.put("url", "http://localhost:8080/v0.1/rest/worker/tbCmd/TEST03");  // return returnCode=00000, empty array
			if (!Flag.flag) info.put("url", "http://localhost:8081/v0.1/rest/worker/tbCmd/TEST01");  // return returnCode=99999
			if (Flag.flag) info.put("url", "http://localhost:8080/v0.1/rest/worker/tbCmd/" + svrCode);
			
			info.put("method", "GET");
			
			MonJsonNode header = new MonJsonNode("{}");
			header.put("Content-Type", "application/json");
			header.put("header01", "value01");
			
			MonJsonNode request = new MonJsonNode("{}");
			request.put("param01", "value01");
			
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
			
			this.nodeCmds = recv.getMonJsonNode("_response");
			log.info("KANG-20210412 >>>>> nodeCmds node: " + this.nodeCmds.toPrettyString());
		}
		
		return this.nodeCmds;
	}
	
	public List<Cmd> jobToListCmds() throws Exception {
		log.info("KANG-20210412 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.lstCmds = new ObjectMapper().readValue(this.nodeCmds.toString(), new TypeReference<List<Cmd>>() {});
		}
		
		return this.lstCmds;
	}
	
	public int jobToAsyncCommands() throws Exception {
		log.info("KANG-20210412 >>>>> {} {}", CurrentInfo.get());
		
		int asyncSize = this.lstCmds.size();
		if (Flag.flag) {
			for (int i=0; i < asyncSize; i++) {
				String param = String.valueOf(i);
				this.asyncCommand(param);
				
				if (!Flag.flag) Sleep.run(1000);
			}
		}
		
		return asyncSize;
	}
	
	@Async(value = "async_Job_Commands")
	private void asyncCommand(String param) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", param, CurrentInfo.get());
	
		if (Flag.flag) {
			
		}
		
		Sleep.run(3000);
	}
}
