package org.tain.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tain.projection.RemodellingWifeInfo;
import org.tain.projection.WifeInfo;
import org.tain.repository.WifeRepository;

@RestController
public class WebController {

	private Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	WifeRepository wifeRepository;
	
	@GetMapping(value="/findwife")
	public WifeInfo getWifeInfo(@RequestParam("firstname") String firstName){
		// Query Data
		WifeInfo wifeInfo = wifeRepository.findWifeByFirstName(firstName);
		
		String wifeToString = String.format("firstname: %s, lastname: %s, age: %d", 
								wifeInfo.getFirstName(), wifeInfo.getLastName(), wifeInfo.getAge());
		log.info("Wife's info: {}", wifeToString);
		
		return wifeInfo;
	}
	
	@GetMapping(value="/findremodelwife")
	public RemodellingWifeInfo getRemodelWifeInfo(@RequestParam("firstname") String firstName){
		// Query Data
		RemodellingWifeInfo wifeInfo = wifeRepository.findRemodelWifeByFisrtName(firstName);
		
		String wifeToString = String.format("fullname: %s, age: %d, husband: %s", 
								wifeInfo.getName(), wifeInfo.getAge(), wifeInfo.getHusband());
		log.info("Re-Modelling Wife's info: {}", wifeToString);
		
		return wifeInfo;
	}
}
