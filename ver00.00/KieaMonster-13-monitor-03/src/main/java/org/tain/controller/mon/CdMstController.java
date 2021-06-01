package org.tain.controller.mon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tain.mybatis.mappers.CdMstMapper;
import org.tain.tools.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.IpPrint;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CdMstController {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private CdMstMapper cdMstMapper;
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/cdMstList"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
		}
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20200730 >>>>> wsUri: {}", wsUri);
			model.addAttribute("wsUri", wsUri);
		}
		
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
		}
		
		if (Boolean.TRUE) {
			List<Map<String,Object>> lst = this.cdMstMapper.selectAll(mapIn);
			log.info("KANG-20200730 >>>>> lst: {}", lst);
			model.addAttribute("lst", lst);
		}
		
		return "web/cmd/cdMstList";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/cdMstForm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String form(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> id: {}",id);
		}
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20200730 >>>>> wsUri: {}", wsUri);
			model.addAttribute("wsUri", wsUri);
		}
		
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
			mapIn = new HashMap<>();
			mapIn.put("id", id);
		}
		
		if (Boolean.TRUE) {
			Map<String,Object> itm = this.cdMstMapper.selectOne(mapIn);
			log.info("KANG-20200730 >>>>> itm: {}", itm);
			model.addAttribute("itm", itm);
		}
		
		return "web/cmd/cdMstForm";
	}
}
