package org.tain.working.campPage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.domain.campPage.CampPage;
import org.tain.httpClient.MonHttpClient;
import org.tain.node.MonJsonNode;
import org.tain.repository.campPage.CampPageRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public void selectAllAndSave() throws Exception {
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
			if (!Flag.flag) {
				// KANG-ERROR: Cannot construct instance of `java.time.LocalDateTime` (no Creators, like default constructor, exist): no String-argument constructor/factory method to deserialize from String value ('2021-03-23T23:25:17.205')
				List<CampPage> lstCampPage = new ObjectMapper().readValue(recv.getArrayNode("_response").toPrettyString(), new TypeReference<List<CampPage>>(){});
				System.out.println(">>>>> lstCampPage.size() = " + lstCampPage.size());
			}
			
			///////////////////////////////////////////////
			//
			if (Flag.flag) {
				ArrayNode arrCampPage = recv.getArrayNode("_response");
				System.out.println(">>>>> arrCampPage.size() = " + arrCampPage.size());
				//JsonNode board = arrBoard.get(0);
				this.index = 0;
				arrCampPage.forEach(jsonNode -> {
					//System.out.println(">>>>> " + jsonNode.toPrettyString());
					try {
						save(jsonNode);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
		}
	}
	
	@Autowired
	private CampPageRepository campPageRepository;
	
	private int index = -1;
	
	private void save(JsonNode jsonNode) throws Exception {
		// log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (!Flag.flag) {
			/*
			System.out.println(">>>>>     " + jsonNode.get("id"));
			System.out.println(">>>>>     " + jsonNode.get("createdDate"));
			*/
		}
		
		if (Flag.flag) {
			///////////////////////////////////////////////
			// 
			CampPage campPage = new ObjectMapper().convertValue(jsonNode, CampPage.class);
			System.out.println("[" + index + "] " + campPage);
			this.campPageRepository.save(campPage);
		}
		
		this.index++;
	}
}
