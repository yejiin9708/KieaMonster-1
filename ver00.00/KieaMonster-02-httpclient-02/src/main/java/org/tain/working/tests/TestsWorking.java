package org.tain.working.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.httpClient.MonHttpClient;
import org.tain.node.MonJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestsWorking {

	@Autowired
	private MonHttpClient monHttpClient;
	
	/*
	 * test for MonJsonNode
	 */
	public void jobTest01() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			MonJsonNode monJsonNode = new MonJsonNode("{}");
			System.out.println(">>>>> type: " + monJsonNode.getType());
			monJsonNode.put("name", "Kiea");
			
			//monJsonNode.put("info", monJsonNode.getNullObjectNode());
			monJsonNode.createObjectNode("info");
			monJsonNode.put("/info", "url", "http://localhost:8080");
			monJsonNode.put("/info", "method", "POST");
			monJsonNode.createObjectNode("/info", "phone");
			monJsonNode.put("/info/phone", "1st", "010");
			monJsonNode.put("/info/phone", "2st", "1234");
			monJsonNode.put("/info/phone", "3st", "5678");
			monJsonNode.put("/info/phone", "4st", "ABCD");
			monJsonNode.put("/info/phone", "2st", "2222");
			
			monJsonNode.createArrayNode("comment");
			monJsonNode.add("/comment", 12345);
			monJsonNode.add("/comment", "hello");
			monJsonNode.add("/comment", monJsonNode.blankObjectNode());
			monJsonNode.put("/comment/2", "createDate", "2021-03-20");
			monJsonNode.put("/comment/2", "createTime", "14:20:45");
			
			System.out.println(">>>>> node: " + monJsonNode.toPrettyString());
			
			monJsonNode.remove("/info/phone", "4st");
			System.out.println(">>>>> remove-1 node: " + monJsonNode.toPrettyString());
			System.out.println(">>>>> phone map node: " + monJsonNode.getMap("/info", "phone"));
			
			monJsonNode.remove("/info", "phone");
			System.out.println(">>>>> remove-2 node: " + monJsonNode.toPrettyString());
			
			monJsonNode.remove("/comment", 2);
			System.out.println(">>>>> remove-3 node: " + monJsonNode.toPrettyString());
			
			monJsonNode.remove("/comment", 0);
			System.out.println(">>>>> remove-4 node: " + monJsonNode.toPrettyString());
			
			//monJsonNode.remove("/", 0);
			//System.out.println(">>>>> remove-4 node: " + monJsonNode.toPrettyString());
		}
	}
	
	/*
	 * test for MonHttpClient
	 */
	public void jobTest02() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			///////////////////////////////////////////////
			// 
			MonJsonNode info = new MonJsonNode("{}");
			info.put("url", "http://localhost:8080/v0.1/api/boards/100");
			info.put("method", "GET");
			
			MonJsonNode header = new MonJsonNode("{}");
			header.put("Content-Type", "application/json");
			header.put("header01", "value01");
			header.put("header02", "value02");
			
			MonJsonNode request = new MonJsonNode("{}");
			request.put("param01", "value01");
			request.put("param02", "value02");
			request.put("param03", "value03");
			
			MonJsonNode response = new MonJsonNode("{}");
			
			///////////////////////////////////////////////
			//
			MonJsonNode send = new MonJsonNode("{}");
			send.put("_info", info);
			send.put("_header", header);
			send.put("_request", request);
			send.put("_response", response);
			System.out.println(">>>>> send node: " + send.toPrettyString());
			
			///////////////////////////////////////////////
			//
			System.out.println(">>>>> info     node: " + send.getMonJsonNode("_info"    ).toPrettyString());
			System.out.println(">>>>> info     node: " + send.getMap("_info"));
			System.out.println(">>>>> header   node: " + send.getMonJsonNode("_header"  ).toPrettyString());
			System.out.println(">>>>> request  node: " + send.getMonJsonNode("_request" ).toPrettyString());
			System.out.println(">>>>> response node: " + send.getMonJsonNode("_response").toPrettyString());
			
			///////////////////////////////////////////////
			//
			MonJsonNode recv = this.monHttpClient.execute(send);
			System.out.println(">>>>> recv node: " + recv.toPrettyString());
		}
	}
	
	/*
	 * test for jobTestGetPage
	 */
	public void jobTestGetPage() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			///////////////////////////////////////////////
			// 
			MonJsonNode info = new MonJsonNode("{}");
			info.put("url", "http://localhost:8080/v0.1/api/boards");
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
			System.out.println(">>>>> RECV NODE: " + recv.toPrettyString());
		}
	}
	
	/*
	 * test for jobTestPost
	 */
	public void jobTestPost() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			///////////////////////////////////////////////
			// 
			MonJsonNode info = new MonJsonNode("{}");
			info.put("url", "http://localhost:8080/v0.1/api/boards");
			info.put("method", "POST");
			
			MonJsonNode header = new MonJsonNode("{}");
			header.put("Content-Type", "application/json");
			header.put("header01", "value01");
			
			MonJsonNode request = new MonJsonNode("{}");
			request.put("title", "제목-XXX");
			request.put("subTitle", "부제목-XXX");
			request.put("content", "안녕하세요.\n내용입니다. 감사합니다. XXX");
			request.put("userId", "kiea");
			
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
			System.out.println(">>>>> RECV NODE: " + recv.toPrettyString());
		}
	}
	
	private static final int BOARD_ID = 605;
	
	/*
	 * test for jobTestGetSingle
	 */
	public void jobTestGetSingle() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			///////////////////////////////////////////////
			// 
			String id = String.valueOf(BOARD_ID);
			
			MonJsonNode info = new MonJsonNode("{}");
			info.put("url", "http://localhost:8080/v0.1/api/boards/" + id);
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
			System.out.println(">>>>> RECV NODE: " + recv.toPrettyString());
		}
	}
	
	/*
	 * test for jobTestPut
	 */
	public void jobTestPut() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			///////////////////////////////////////////////
			// 
			String id = String.valueOf(BOARD_ID);
			
			MonJsonNode info = new MonJsonNode("{}");
			info.put("url", "http://localhost:8080/v0.1/api/boards/" + id);
			info.put("method", "PUT");
			
			MonJsonNode header = new MonJsonNode("{}");
			header.put("Content-Type", "application/json");
			header.put("header01", "value01");
			
			MonJsonNode request = new MonJsonNode("{}");
			request.put("title", "제목-" + id);
			request.put("subTitle", "부제목-" + id);
			request.put("content", "안녕하세요.\n내용입니다. 감사합니다. by Kiea - " + id);
			request.put("userId", "kiea");
			request.put("createdDate", "2021-03-28T23:39:13.126");
			
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
			System.out.println(">>>>> RECV NODE: " + recv.toPrettyString());
		}
	}
	
	/*
	 * test for jobTestDelete
	 */
	public void jobTestDelete() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			///////////////////////////////////////////////
			// 
			String id = String.valueOf(BOARD_ID);
			
			MonJsonNode info = new MonJsonNode("{}");
			info.put("url", "http://localhost:8080/v0.1/api/boards/" + id);
			info.put("method", "DELETE");
			
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
			System.out.println(">>>>> RECV NODE: " + recv.toPrettyString());
		}
	}
}
