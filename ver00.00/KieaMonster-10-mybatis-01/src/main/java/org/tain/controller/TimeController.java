package org.tain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.service.TimeService;

@RestController
@RequestMapping("/time")
public class TimeController {

	@Autowired
	private TimeService timeService;
	
	@GetMapping("/1")
	public String now1() {
		String now1 = this.timeService.getNow1();
		return now1;
	}
	
	@GetMapping("/2")
	public String now2() {
		String now2 = this.timeService.getNow2();
		return now2;
	}
}
