package org.tain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tain.tools.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FirstController {

	@RequestMapping("/")
	public String index() {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		return "index";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@RequestMapping("/ws")
	public String ws() {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		String wsUri = this.projEnvUrlProperties.getWsUri();
		log.info("KANG-20210405 >>>>> wsUrl: {}", wsUri);
		return "ws/ws";
	}
}
