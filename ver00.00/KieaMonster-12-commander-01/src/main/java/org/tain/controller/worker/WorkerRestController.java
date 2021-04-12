package org.tain.controller.worker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.domain.TbCmd;
import org.tain.properties.ProjEnvBaseProperties;
import org.tain.repository.TbCmdRepository;
import org.tain.service.worker.TbCmdService;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/rest/worker"})
@Slf4j
public class WorkerRestController {

	@Autowired
	private ProjEnvBaseProperties projEnvBaseProperties;
	
	@Autowired
	private TbCmdService tbCmdService;
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@GetMapping(value = {"/tbCmd/{svrCode}"})
	public ResponseEntity<?> listBySvrCode(@PathVariable(value = "svrCode") String svrCode) throws Exception {
		log.info("KANG-20210412 >>>>> {} {}", CurrentInfo.get());
		
		List<TbCmd> list = this.tbCmdService.listBySvrCode(svrCode);
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=" + this.projEnvBaseProperties.getCharSet());
		
		return new ResponseEntity<>(list, headers, HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbCmdRepository tbCmdRepository;
	
	@RequestMapping(value = {"/tbCmd"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> list(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		List<TbCmd> list = this.tbCmdRepository.findAll();
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=" + this.projEnvBaseProperties.getCharSet());
		
		return new ResponseEntity<>(list, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/tbCmd2"}, method = {RequestMethod.GET, RequestMethod.POST})
	public List<TbCmd> list2() throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		return this.tbCmdRepository.findAll();
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
