package org.tain.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/rest/test"})
@Slf4j
public class TestRestController {

	@RequestMapping(value = {"/list"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> list(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		List<Map<?,?>> list = this.getList();
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		//headers.add("Content-Type", "application/json; charset=UTF-8");
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(list, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/list2"}, method = {RequestMethod.GET, RequestMethod.POST})
	public List<Map<?,?>> list2() throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		return this.getList();
	}
	
	private List<Map<?,?>> getList() {
		Map<String,Object> map = new HashMap<>();
		map.put("name", "Kiea 강석");
		map.put("date", LocalDateTime.now());
		map.put("message", "Hello, world !!!");
		
		List<Map<?,?>> list = new ArrayList<>();
		list.add(map);
		return list;
	}
}
