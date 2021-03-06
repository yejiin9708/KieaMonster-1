package org.tain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tain.tools.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.IpPrint;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/"})
@Slf4j
public class HomeController {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value={"", "home"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String index(Model model) {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			model.addAttribute("wsUri", this.projEnvUrlProperties.getWsUri());
			log.info("KANG-20210405 >>>>> wsUrl: {}", model.getAttribute("wsUri"));
		}
		
		return "home";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("/ws")
	public String ws() {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		IpPrint.print();
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20210405 >>>>> wsUrl: {}", wsUri);
		}
		return "ws/ws";
	}
}
