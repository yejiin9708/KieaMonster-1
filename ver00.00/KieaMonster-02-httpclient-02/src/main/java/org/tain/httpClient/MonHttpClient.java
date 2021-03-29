package org.tain.httpClient;

import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.tain.node.MonJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MonHttpClient {

	/*
	 * 
	 */
	public MonJsonNode execute(MonJsonNode trans) throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		log.info("=========================== MonHttpClient.execute START =============================");
		
		if (Flag.flag) {
			MonJsonNode info = trans.getMonJsonNode("_info");
			String method = info.getText("method").toUpperCase();
			switch (method) {
			case "GET":
				trans = get(trans, method);
				break;
			case "POST":
			case "PUT":
			case "DELETE":
				trans = post(trans, method);
				break;
			default:
				break;
			}
		}
		
		log.info("=========================== MonHttpClient.execute END =============================");
		
		return trans;
	}
	
	/*
	 * 
	 */
	private MonJsonNode get(MonJsonNode trans, String method) throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		MultiValueMap<String, String> mapParam = null;
		if (Flag.flag) {
			@SuppressWarnings("unchecked")
			Map<String,String> mapRequest = (Map<String,String>) trans.getMap("_request");
			
			mapParam = new LinkedMultiValueMap<>();
			mapParam.setAll(mapRequest);
			log.info(">>>>> {}-mapParam = {}", method, mapParam);
		}
		
		String httpUrl = null;
		if (Flag.flag) {
			httpUrl = trans.getText("/_info", "url");
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
					.queryParams(mapParam)
					.build(true);
			httpUrl = builder.toString();
			log.info(">>>>> {}-httpUrl = {}", method, httpUrl);
		}
		
		HttpMethod httpMethod = null;
		if (Flag.flag) {
			httpMethod = HttpMethod.valueOf(trans.getText("/_info", "method").toUpperCase());
			log.info(">>>>> {}-httpMethod = {}", method, httpMethod);
		}
		
		HttpHeaders reqHeaders = null;
		if (Flag.flag) {
			reqHeaders = new HttpHeaders();
			//reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			@SuppressWarnings("unchecked")
			Map<String,String> mapHeader = (Map<String,String>) trans.getMap("_header");
			for (Map.Entry<String,String> header : mapHeader.entrySet()) {
				reqHeaders.set(header.getKey(), header.getValue());
			}
			
			log.info(">>>>> {}-reqHeaders = {}", method, reqHeaders);
		}
		
		HttpEntity<String> reqHttpEntity = null;
		if (Flag.flag) {
			reqHttpEntity = new HttpEntity<>(reqHeaders);
			log.info(">>>>> {}-reqHttpEntity  = {}", method, reqHttpEntity);
		}
		
		ResponseEntity<String> response = null;
		try {
			response = getCustomRestTemplate().exchange(
					httpUrl
					, httpMethod
					, reqHttpEntity
					, String.class);
			
			String responseBody = response.getBody();
			
			log.info(">>>>> {}-getStatusCodeValue() = {}", method, response.getStatusCodeValue());
			log.info(">>>>> {}-getStatusCode()      = {}", method, response.getStatusCode());
			log.info(">>>>> {}-getBody()            = {}", method, responseBody);
			
			//if (Flag.flag) throw new Exception("template error");
			
			trans.put("/_info", "returnCode", "00000");
			trans.put("/_info", "returnMessage", "SUCCESS");
			trans.put("/_info", "statusCodeValue", response.getStatusCodeValue());
			trans.put("/_info", "statusCode", String.valueOf(response.getStatusCode()));
			trans.put("_response", new MonJsonNode(responseBody != null ? responseBody : "{}"));
		} catch (Exception e) {
			//e.printStackTrace();
			String message = e.getMessage();
			log.error("ERROR >>>>> {}", message);
			//int pos1 = message.indexOf('[');
			//int pos2 = message.lastIndexOf(']');
			trans.put("/_info", "returnCode", "99999");
			trans.put("/_info", "returnMessage", "ERROR");
			trans.put("/_info", "errorMessage", message);
		}
		
		return trans;
	}
	
	/*
	 * 
	 */
	private MonJsonNode post(MonJsonNode trans, String method) throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		String httpUrl = null;
		if (Flag.flag) {
			httpUrl = trans.getText("/_info", "url");
			log.info(">>>>> {}-httpUrl = {}", method, httpUrl);
		}
		
		HttpMethod httpMethod = null;
		if (Flag.flag) {
			httpMethod = HttpMethod.valueOf(trans.getText("/_info", "method").toUpperCase());
			log.info(">>>>> {}-httpMethod = {}", method, httpMethod);
		}
		
		HttpHeaders reqHeaders = null;
		if (Flag.flag) {
			reqHeaders = new HttpHeaders();
			//reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			@SuppressWarnings("unchecked")
			Map<String,String> mapHeader = (Map<String,String>) trans.getMap("_header");
			for (Map.Entry<String,String> header : mapHeader.entrySet()) {
				reqHeaders.set(header.getKey(), header.getValue());
			}
			
			log.info(">>>>> {}-reqHeaders = {}", method, reqHeaders);
		}
		
		HttpEntity<String> reqHttpEntity = null;
		if (Flag.flag) {
			JsonNode request = trans.getJsonNode("_request");
			reqHttpEntity = new HttpEntity<>(request != null ? request.toPrettyString() : null, reqHeaders);
			log.info(">>>>> {}-reqHttpEntity  = {}", method, reqHttpEntity);
		}
		
		ResponseEntity<String> response = null;
		try {
			response = getCustomRestTemplate().exchange(
					httpUrl
					, httpMethod
					, reqHttpEntity
					, String.class);
			
			String responseBody = response.getBody();
			
			log.info(">>>>> {}-getStatusCodeValue() = {}", method, response.getStatusCodeValue());
			log.info(">>>>> {}-getStatusCode()      = {}", method, response.getStatusCode());
			log.info(">>>>> {}-getBody()            = {}", method, responseBody);
			
			//if (Flag.flag) throw new Exception("template error");
			
			trans.put("/_info", "returnCode", "00000");
			trans.put("/_info", "returnMessage", "SUCCESS");
			trans.put("/_info", "statusCodeValue", response.getStatusCodeValue());
			trans.put("/_info", "statusCode", String.valueOf(response.getStatusCode()));
			trans.put("_response", new MonJsonNode(responseBody != null ? responseBody : "{}"));
		} catch (Exception e) {
			//e.printStackTrace();
			String message = e.getMessage();
			log.error("ERROR >>>>> {}", message);
			//int pos1 = message.indexOf('[');
			//int pos2 = message.lastIndexOf(']');
			trans.put("/_info", "returnCode", "99999");
			trans.put("/_info", "returnMessage", "ERROR");
			trans.put("/_info", "errorMessage", message);
		}
		
		return trans;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private RestTemplate getCustomRestTemplate() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout(10 * 1000);
		httpRequestFactory.setReadTimeout(60 * 1000);
		HttpClient httpClient = HttpClientBuilder.create()
				.setMaxConnTotal(200)
				.setMaxConnPerRoute(20)
				.build();
		httpRequestFactory.setHttpClient(httpClient);
		return new RestTemplate(httpRequestFactory);
	}
}
