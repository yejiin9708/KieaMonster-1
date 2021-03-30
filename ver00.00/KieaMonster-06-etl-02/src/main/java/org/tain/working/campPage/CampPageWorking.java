package org.tain.working.campPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.httpClient.MonHttpClient;
import org.tain.node.MonJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CampPageWorking {

	@Autowired
	private MonHttpClient monHttpClient;
	
	/*
	 * test for selectAll
	 */
	public void selectAllAndSend() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			///////////////////////////////////////////////
			// 
			MonJsonNode info = new MonJsonNode("{}");
			info.put("url", "http://localhost:8080/v0.1/rest/campPage/list");
			//info.put("url", "http://localhost:8080/v0.1/rest/campPage/list2");
			info.put("method", "GET");
			
			MonJsonNode header = new MonJsonNode("{}");
			header.put("Content-Type", "application/json");
			header.put("header01", "value01");
			
			MonJsonNode request = new MonJsonNode("{}");
			
			MonJsonNode response = new MonJsonNode("{}");
			
			///////////////////////////////////////////////
			//
			MonJsonNode send = new MonJsonNode("{}");
			send.put("_info", info);
			send.put("_header", header);
			send.put("_request", request);
			send.put("_response", response);
			System.out.println(">>>>> SEND NODE: " + send.toPrettyString());
			
			///////////////////////////////////////////////
			//
			MonJsonNode recv = this.monHttpClient.execute(send);
			//System.out.println(">>>>> RECV NODE: " + recv.toPrettyString());
			
			///////////////////////////////////////////////
			//
			if (Flag.flag) {
				ArrayNode arrCampPage = recv.getArrayNode("_response");
				System.out.println(">>>>> arrCampPage.size() = " + arrCampPage.size());
				//JsonNode board = arrCampPage.get(0);
				this.index = 0;
				arrCampPage.forEach(jsonNode -> {
					//System.out.println(">>>>> " + jsonNode.toPrettyString());
					try {
						post(jsonNode);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
		}
	}
	
	private int index = -1;
	
	private void post(JsonNode jsonNode) throws Exception {
		// log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (!Flag.flag) {
			//System.out.println(">>>>>  (" + this.index + ")   " + jsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			///////////////////////////////////////////////
			// 
			MonJsonNode info = new MonJsonNode("{}");
			info.put("url", "http://localhost:8083/v0.1/api/campPages");
			info.put("method", "POST");
			
			MonJsonNode header = new MonJsonNode("{}");
			header.put("Content-Type", "application/json");
			header.put("header01", "value01");
			
			MonJsonNode request = new MonJsonNode(jsonNode);
			
			MonJsonNode response = new MonJsonNode("{}");
			
			///////////////////////////////////////////////
			//
			MonJsonNode send = new MonJsonNode("{}");
			send.put("_info", info);
			send.put("_header", header);
			send.put("_request", request);
			send.put("_response", response);
			System.out.println(">>>>> (" + this.index + ")  SEND NODE: " + send.toPrettyString());
			
			///////////////////////////////////////////////
			//
			MonJsonNode recv = this.monHttpClient.execute(send);
			System.out.println(">>>>> (" + this.index + ")  RECV NODE: " + recv.toPrettyString());
		}
		
		this.index++;
	}
}
