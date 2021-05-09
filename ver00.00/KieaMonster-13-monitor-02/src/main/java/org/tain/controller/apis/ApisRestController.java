package org.tain.controller.apis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/rest/apis"})
//@Slf4j
public class ApisRestController {

	/*
	@Autowired
	private ApisRepository apisRepository;
	
	@RequestMapping(value = {"/list1"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> list1(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		List<Apis> lst = this.apisRepository.findAll();
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lst, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/list2"}, method = {RequestMethod.GET, RequestMethod.POST})
	public List<Apis> list2() throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		return this.apisRepository.findAll();
	}
	
	@RequestMapping(value = {"/page1"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> page1(HttpEntity<String> httpEntity) throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		Pageable pageable = PageRequest.of(0, 3);
		Page<Apis> lst = this.apisRepository.findAll(pageable);
		
		MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(lst, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/page2"}, method = {RequestMethod.GET, RequestMethod.POST})
	public Page<Apis> page2() throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		return this.apisRepository.findAll(PageRequest.of(0, 3));
	}
	*/
}
