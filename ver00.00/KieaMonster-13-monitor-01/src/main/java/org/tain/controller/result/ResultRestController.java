package org.tain.controller.result;

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
import org.tain.properties.ProjEnvBaseProperties;
import org.tain.repository.TbResultRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/rest/result"})
@Slf4j
public class ResultRestController {

	@Autowired
	private ProjEnvBaseProperties projEnvBaseProperties;
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	@Autowired
	private TbResultRepository tbResultRepository;
	
	@RequestMapping(value = {"/tbResult"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> list(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		System.out.println(">>>>> 1. httpEntity.getHeaders(): " + httpEntity.getHeaders());
		System.out.println(">>>>> 2. httpEntity.getBody(): " + httpEntity.getBody());
		
		if (Flag.flag) {
			// send the message
		}
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=" + this.projEnvBaseProperties.getCharSet());
		
		return new ResponseEntity<>("{}", headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	@GetMapping(value = {"/list/{userId}"})
	public String listByUserId(@PathVariable(value = "userId") String userId, Pageable pageable, Model model) {
		log.info("KANG-20200803 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("boardList", this.boardService.findBoardListByUserId(pageable, userId));
		return "board/list";
	}
	*/
}
