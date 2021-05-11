package org.tain.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tain.tools.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/"})
@Slf4j
public class HomeController {

	@RequestMapping(value={"", "home"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String index() {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		if (Boolean.TRUE) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null)
				ip = request.getRemoteAddr();
			System.out.println(">>>>> Client IP: " + ip);
		}
		return "home";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@RequestMapping("/ws")
	public String ws() {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		/*
		if (Boolean.TRUE) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null)
				ip = request.getRemoteAddr();
			System.out.println(">>>>> Client IP: " + ip);
		}
		*/
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20210405 >>>>> wsUrl: {}", wsUri);
		}
		return "ws/ws";
	}
}
