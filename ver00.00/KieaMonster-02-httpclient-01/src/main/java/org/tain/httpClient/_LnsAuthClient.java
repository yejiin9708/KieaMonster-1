package org.tain.httpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tain.data._LnsData;
import org.tain.mapper._LnsJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils._RestTemplateConfig;
import org.tain.utils.enums._RestTemplateType;

import lombok.extern.slf4j.Slf4j;

@Deprecated
@Component
@Slf4j
public class _LnsAuthClient {

	@Autowired
	private _LnsData lnsData;
	
	public _LnsJsonNode auth(_LnsJsonNode lnsJsonNode) throws Exception {
		
		log.info("=========================== LnsAuthClient.auth =============================");
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info(">>>>> AUTH.REQ.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			String httpUrl = lnsJsonNode.getText("httpUrl");
			HttpMethod httpMethod = HttpMethod.POST;
			
			_LnsJsonNode reqJsonNode = new _LnsJsonNode(lnsJsonNode.getJsonNode("reqJson"));
			log.info(">>>>> AUTH.REQ.reqJsonNode    = {}", reqJsonNode.toPrettyString());
			
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			log.info(">>>>> AUTH.REQ.reqHeaders     = {}", reqHeaders);
			
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqJsonNode.toPrettyString(), reqHeaders);
			log.info(">>>>> AUTH.REQ.reqHttpEntity  = {}", reqHttpEntity);
			
			ResponseEntity<String> response = null;
			try {
				response = _RestTemplateConfig.get(_RestTemplateType.SETENV).exchange(
						httpUrl
						, httpMethod
						, reqHttpEntity
						, String.class);
				
				log.info(">>>>> AUTH.RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> AUTH.RES.getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> AUTH.RES.getBody()            = {}", response.getBody());
				
				this.lnsData.setAccessToken(response.getHeaders().get("AccessToken").get(0));
				log.info(">>>>> AUTH.RES.accessToken          = {}", this.lnsData.getAccessToken());
				
				lnsJsonNode.put("code", "00000");
				lnsJsonNode.put("status", "success");
				lnsJsonNode.put("message", "OK");
			} catch (Exception e) {
				//e.printStackTrace();
				String message = e.getMessage();
				log.error("ERROR >>>>> {}", message);
				int pos1 = message.indexOf('[');
				int pos2 = message.lastIndexOf(']');
				lnsJsonNode.put("code", "99999");
				lnsJsonNode.put("status", "fail");
				lnsJsonNode.put("message", message.substring(pos1 + 1, pos2));
			}
		}
		
		if (Flag.flag) {
			log.info(">>>>> AUTH.RES.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
		}
		
		log.info("--------------------------- LnsAuthClient.auth -----------------------------");
		
		return lnsJsonNode;
	}
}
