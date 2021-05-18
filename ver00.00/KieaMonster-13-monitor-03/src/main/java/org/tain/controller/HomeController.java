package org.tain.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tain.db.domain.TbGrp;
import org.tain.db.domain.TbSvr;
import org.tain.db.repository.TbGrpRepository;
import org.tain.db.repository.TbSvrRepository;
import org.tain.tools.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/"})
@Slf4j
public class HomeController {

	@Autowired
	private TbGrpRepository tbGrpRepository;
	
	@Autowired
	private TbSvrRepository tbSvrRepository;
	
	@RequestMapping(value={"", "home"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String index(Model model) {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null)
				ip = request.getRemoteAddr();
			System.out.println(">>>>> Client IP: " + ip);
		}
		
		if (Boolean.TRUE) {
			List<TbGrp> lstGrp = this.tbGrpRepository.findAll();
			model.addAttribute("lstGrp", lstGrp);
			System.out.println(">>>>> lstGrp: " + lstGrp);
		}
		
		if (Boolean.TRUE) {
			List<TbSvr> lstSvr = this.tbSvrRepository.findAll();
			Map<String, String> mapSvr = new LinkedHashMap<>();
			for (TbSvr svr : lstSvr) {
				mapSvr.put(svr.getSvrCode(), svr.getGrpCode() + "-" + svr.getSvrCode());
			}
			model.addAttribute("mapSvr", mapSvr);
			model.addAttribute("lstSvr", lstSvr);
			System.out.println(">>>>> lstSvr: " + lstSvr);
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
