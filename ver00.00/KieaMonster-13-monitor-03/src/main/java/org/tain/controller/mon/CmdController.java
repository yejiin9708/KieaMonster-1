package org.tain.controller.mon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tain.mybatis.mappers.CmdMapper;
import org.tain.tools.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.IpPrint;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CmdController {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private CmdMapper cmdMapper;
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/cmdList"}, method = {RequestMethod.GET, RequestMethod.POST})
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
			List<Map<String,Object>> lst = this.cmdMapper.selectAll(mapIn);
			log.info("KANG-20200730 >>>>> lst: {}", lst);
			model.addAttribute("lst", lst);
		}
		
		return "web/cmd/cmdList";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/cmdForm"}, method = {RequestMethod.GET, RequestMethod.POST})
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
			Map<String,Object> itm = this.cmdMapper.selectOne(mapIn);
			log.info("KANG-20200730 >>>>> itm: {}", itm);
			model.addAttribute("itm", itm);
		}
		
		return "web/cmd/cmdForm";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/cmdView"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String view(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
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
			Map<String,Object> itm = this.cmdMapper.selectOne(mapIn);
			log.info("KANG-20200730 >>>>> itm: {}", itm);
			model.addAttribute("itm", itm);
		}
		
		return "web/cmd/cmdView";
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/grpCmdList/{code}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String listByGrp(@PathVariable(value = "code") String code, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> code: {}",code);
		}
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20200730 >>>>> wsUri: {}", wsUri);
			model.addAttribute("wsUri", wsUri);
		}
		
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
			mapIn = new HashMap<>();
			mapIn.put("code", code);
		}
		
		if (Boolean.TRUE) {
			List<Map<String,Object>> lst = this.cmdMapper.selectAllByGrp(mapIn);
			log.info("KANG-20200730 >>>>> lst: {}", lst);
			model.addAttribute("lst", lst);
			model.addAttribute("code", code);
		}
		
		return "web/cmd/grpCmdList";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/grpCmdForm/{code}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String formByGrp(@PathVariable(value = "code") String code, @RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> code: {}, id: {}", code, id);
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
			mapIn.put("code", code);
		}
		
		if (Boolean.TRUE) {
			Map<String,Object> itm = this.cmdMapper.selectOneByGrp(mapIn);
			log.info("KANG-20200730 >>>>> itm: {}", itm);
			model.addAttribute("itm", itm);
			model.addAttribute("code", code);
		}
		
		return "web/cmd/grpCmdForm";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/grpCmdView/{code}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String viewByGrp(@PathVariable(value = "code") String code, @RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> code: {}, id: {}", code, id);
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
			mapIn.put("code", code);
		}
		
		if (Boolean.TRUE) {
			Map<String,Object> itm = this.cmdMapper.selectOneByGrp(mapIn);
			log.info("KANG-20200730 >>>>> itm: {}", itm);
			model.addAttribute("itm", itm);
			model.addAttribute("code", code);
		}
		
		return "web/cmd/grpCmdView";
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/svrCmdList/{code}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String listBySvr(@PathVariable(value = "code") String code, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> code: {}",code);
		}
		
		if (Boolean.TRUE) {
			String wsUri = this.projEnvUrlProperties.getWsUri();
			log.info("KANG-20200730 >>>>> wsUri: {}", wsUri);
			model.addAttribute("wsUri", wsUri);
		}
		
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
			mapIn = new HashMap<>();
			mapIn.put("code", code);
		}
		
		if (Boolean.TRUE) {
			List<Map<String,Object>> lst = this.cmdMapper.selectAllBySvr(mapIn);
			log.info("KANG-20200730 >>>>> lst: {}", lst);
			model.addAttribute("lst", lst);
			model.addAttribute("code", code);
		}
		
		return "web/cmd/svrCmdList";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/svrCmdForm/{code}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String formBySvr(@PathVariable(value = "code") String code, @RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> code: {}, id: {}", code, id);
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
			mapIn.put("code", code);
		}
		
		if (Boolean.TRUE) {
			Map<String,Object> itm = this.cmdMapper.selectOneBySvr(mapIn);
			log.info("KANG-20200730 >>>>> itm: {}", itm);
			model.addAttribute("itm", itm);
			model.addAttribute("code", code);
		}
		
		return "web/cmd/svrCmdForm";
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/cmd/svrCmdView/{code}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String viewBySvr(@PathVariable(value = "code") String code, @RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			IpPrint.print();
			log.info("KANG-20200730 >>>>> code: {}, id: {}", code, id);
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
			mapIn.put("code", code);
		}
		
		if (Boolean.TRUE) {
			Map<String,Object> itm = this.cmdMapper.selectOneBySvr(mapIn);
			log.info("KANG-20200730 >>>>> itm: {}", itm);
			model.addAttribute("itm", itm);
			model.addAttribute("code", code);
		}
		
		return "web/cmd/svrCmdView";
	}
	
}
