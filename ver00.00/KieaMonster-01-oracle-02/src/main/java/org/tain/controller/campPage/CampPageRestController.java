package org.tain.controller.campPage;

import java.util.List;

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
import org.tain.domain.campPage.CampPage;
import org.tain.repository.campPage.CampPageRepository;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/rest/campPage"})
@Slf4j
public class CampPageRestController {

	@Autowired
	private CampPageRepository campPageRepository;
	
	@RequestMapping(value = {"/list"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> list(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20210330 >>>>> {} {}", CurrentInfo.get());
		
		List<CampPage> list = this.campPageRepository.findAll();
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		//headers.add("Content-Type", "application/json; charset=UTF-8");
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(list, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/list2"}, method = {RequestMethod.GET, RequestMethod.POST})
	public List<CampPage> list2() throws Exception {
		log.info("KANG-20210330 >>>>> {} {}", CurrentInfo.get());
		return this.campPageRepository.findAll();
	}
}
