package org.tain.controller.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tain.db.domain.TbCmd;
import org.tain.db.service.TbCmdService;
import org.tain.tools.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/cmd"})
@Slf4j
public class ApisController {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private TbCmdService tbCmdService;
	
	@RequestMapping(value = {"/list"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Pageable pageable, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		Page<TbCmd> pageTbCmd = this.tbCmdService.listAll(pageable);
		log.info("KANG-20200730 >>>>> pageTbCmd: {}", pageTbCmd);
		model.addAttribute("cmdList", pageTbCmd);
		//model.addAttribute("urlOnline", this.projEnvUrlProperties.getOnline());
		//model.addAttribute("urlMapper", this.projEnvUrlProperties.getMapper());
		//model.addAttribute("urlSbs01", this.projEnvUrlProperties.getLns01());
		return "web/cmd/list";
	}
	
	@RequestMapping(value = {"/form"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String form(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		TbCmd tbCmd = this.tbCmdService.getOne(id);
		log.info("KANG-20200730 >>>>> tbCmd: {}", tbCmd);
		model.addAttribute("cmd", tbCmd);
		model.addAttribute("wsUri", this.projEnvUrlProperties.getWsUri());
		//model.addAttribute("urlOnline", this.projEnvUrlProperties.getOnline());
		//model.addAttribute("urlMapper", this.projEnvUrlProperties.getMapper());
		//model.addAttribute("urlLns01", this.projEnvUrlProperties.getLns01());
		return "web/cmd/form";
	}
	
	/*
	@RequestMapping(value = {""}, method = {RequestMethod.GET, RequestMethod.POST})
	public String form(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("apis", this.apisService.findApisById(id));
		model.addAttribute("urlOnline", this.projEnvUrlProperties.getOnline());
		model.addAttribute("urlMapper", this.projEnvUrlProperties.getMapper());
		model.addAttribute("urlSbs01", this.projEnvUrlProperties.getLns01());
		return "web/apis/form";
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/pingForm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String pingForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		//model.addAttribute("apis", this.apisService.findApisById(id));
		//return "web/apis/pingForm";
		return "redirect:/apis/list";
		//return new RedirectView("/apis/list");
		//return "redirect:/apis/list";
		//return new RedirectView("/apis/list");
	}
	
	@RequestMapping(value = {"/encryptForm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String encryptForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		//model.addAttribute("apis", this.apisService.findApisById(id));
		//return "web/apis/encryptForm";
		return "redirect:/apis/list";
		//return new RedirectView("/apis/list");
	}
	
	@RequestMapping(value = {"/decryptForm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String decryptForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		//model.addAttribute("apis", this.apisService.findApisById(id));
		//return "web/apis/decryptForm";
		return "redirect:/apis/list";
		//return new RedirectView("/apis/list");
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = {"/getFeeForm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String getFeeForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("apis", this.apisService.findApisById(id));
		model.addAttribute("urlOnline", this.projEnvUrlProperties.getOnline());
		model.addAttribute("urlMapper", this.projEnvUrlProperties.getMapper());
		model.addAttribute("urlLns01", this.projEnvUrlProperties.getLns01());
		return "web/apis/getFeeForm";
	}
	
	@RequestMapping(value = {"/validateForm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String validateForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("apis", this.apisService.findApisById(id));
		model.addAttribute("urlOnline", this.projEnvUrlProperties.getOnline());
		model.addAttribute("urlMapper", this.projEnvUrlProperties.getMapper());
		model.addAttribute("urlLns01", this.projEnvUrlProperties.getLns01());
		return "web/apis/validateForm";
	}
	
	@RequestMapping(value = {"/commitForm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String commitForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("apis", this.apisService.findApisById(id));
		model.addAttribute("urlOnline", this.projEnvUrlProperties.getOnline());
		model.addAttribute("urlMapper", this.projEnvUrlProperties.getMapper());
		model.addAttribute("urlLns01", this.projEnvUrlProperties.getLns01());
		return "web/apis/commitForm";
	}
	
	@RequestMapping(value = {"/amendForm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String amendForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("apis", this.apisService.findApisById(id));
		model.addAttribute("urlOnline", this.projEnvUrlProperties.getOnline());
		model.addAttribute("urlMapper", this.projEnvUrlProperties.getMapper());
		model.addAttribute("urlLns01", this.projEnvUrlProperties.getLns01());
		return "web/apis/amendForm";
	}
	
	@RequestMapping(value = {"/refundForm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String refundForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("apis", this.apisService.findApisById(id));
		model.addAttribute("urlOnline", this.projEnvUrlProperties.getOnline());
		model.addAttribute("urlMapper", this.projEnvUrlProperties.getMapper());
		model.addAttribute("urlLns01", this.projEnvUrlProperties.getLns01());
		return "web/apis/refundForm";
	}
	
	@RequestMapping(value = {"/detailForm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String detailForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20210329 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("apis", this.apisService.findApisById(id));
		model.addAttribute("urlOnline", this.projEnvUrlProperties.getOnline());
		model.addAttribute("urlMapper", this.projEnvUrlProperties.getMapper());
		model.addAttribute("urlLns01", this.projEnvUrlProperties.getLns01());
		return "web/apis/detailForm";
	}
	
	@RequestMapping(value = {"/historyForm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String historyForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20210329 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("apis", this.apisService.findApisById(id));
		model.addAttribute("urlOnline", this.projEnvUrlProperties.getOnline());
		model.addAttribute("urlMapper", this.projEnvUrlProperties.getMapper());
		model.addAttribute("urlLns01", this.projEnvUrlProperties.getLns01());
		return "web/apis/historyForm";
	}
	
	@RequestMapping(value = {"/customerForm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String customerForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
		log.info("KANG-20210329 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("apis", this.apisService.findApisById(id));
		model.addAttribute("urlOnline", this.projEnvUrlProperties.getOnline());
		model.addAttribute("urlMapper", this.projEnvUrlProperties.getMapper());
		model.addAttribute("urlLns01", this.projEnvUrlProperties.getLns01());
		return "web/apis/customerForm";
	}
	*/
}
