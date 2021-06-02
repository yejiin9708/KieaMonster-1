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
import org.tain.httpClient.MonHttpClient;
import org.tain.tools.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.IpPrint;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/commander")
@Slf4j
public class CommanderController {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private MonHttpClient monHttpClient;
	
	///////////////////////////////////////////////////////////////////////////
	
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
		
		String httpUrl = this.projEnvUrlProperties.getWorkerUrl() + "/worker/cmd/start";
		log.info(">>>>> httpUrl: " + httpUrl);
		String resData = this.monHttpClient.post(httpUrl, reqData);
		
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
		
		String httpUrl = this.projEnvUrlProperties.getWorkerUrl() + "/worker/cmd/stop";
		log.info(">>>>> httpUrl: " + httpUrl);
		String resData = this.monHttpClient.post(httpUrl, reqData);
		
		MultiValueMap<String,String> headers = null;
		if (Boolean.TRUE) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		
		return new ResponseEntity<>(resData, headers, HttpStatus.OK);
	}
}
