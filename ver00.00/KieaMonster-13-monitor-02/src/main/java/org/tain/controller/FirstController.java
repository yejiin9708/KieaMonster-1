package org.tain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FirstController {

	@RequestMapping("/")
	public String index() {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		return "/index";
	}
	
	@RequestMapping("/ws")
	public String ws() {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		return "/ws/ws";
	}
}
