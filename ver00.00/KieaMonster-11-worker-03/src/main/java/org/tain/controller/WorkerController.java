package org.tain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.data.Cmd;
import org.tain.tasks.asyncCommand.AsyncCommandTask;
import org.tain.tools.node.MonJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.IpPrint;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/worker")
@Slf4j
public class WorkerController {

	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private AsyncCommandTask asyncCommandTask;
	
	@RequestMapping(value = {"/cmd/start"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> cmdStart(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		String reqData = null;
		if (Boolean.TRUE) {
			HttpHeaders headers = httpEntity.getHeaders();
			reqData = httpEntity.getBody();
			IpPrint.print();
			log.info(">>>>> request.headers: " + headers.toString());
			log.info(">>>>> request.body: " + reqData);
		}
		
		String resData = null;
		if (Boolean.TRUE) {
			MonJsonNode reqNode = new MonJsonNode(reqData);
			log.info(">>>>> reqNode: " + reqNode.toPrettyString());
			
			Cmd cmd = new Cmd();
			cmd.setId(Long.parseLong(reqNode.getText("id")));
			cmd.setCmdArr(reqNode.getText("cmdArr"));
			cmd.setCmdPeriod(reqNode.getText("cmdPeriod"));
			
			this.asyncCommandTask.async0103(cmd);
			
			resData = "{\"msgCode\": \"MC00011\", \"status\": \"SUCCESS\"}";
		}
		
		MultiValueMap<String,String> headers = null;
		if (Boolean.TRUE) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		return new ResponseEntity<>(resData, headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/stop"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> cmdStop(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		String reqData = null;
		if (Boolean.TRUE) {
			HttpHeaders headers = httpEntity.getHeaders();
			reqData = httpEntity.getBody();
			IpPrint.print();
			log.info(">>>>> request.headers: " + headers.toString());
			log.info(">>>>> request.body: " + reqData);
		}
		
		String resData = null;
		if (Boolean.TRUE) {
			MonJsonNode reqNode = new MonJsonNode(reqData);
			log.info(">>>>> reqNode: " + reqNode.toPrettyString());
			
			this.asyncCommandTask.stopAsync();
			
			resData = "{\"msgCode\": \"MC00091\", \"status\": \"SUCCESS\"}";
		}
		
		MultiValueMap<String,String> headers = null;
		if (Boolean.TRUE) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		return new ResponseEntity<>(resData, headers, HttpStatus.OK);
	}
}
