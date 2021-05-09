package org.tain.controller.apis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/apis"})
//@Slf4j
public class ApisController {

	/*
	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private ApisService apisService;
	
	@RequestMapping(value = {"/list"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Pageable pageable, Model model) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		model.addAttribute("apisList", this.apisService.findApisList(pageable));
		model.addAttribute("urlOnline", this.projEnvUrlProperties.getOnline());
		model.addAttribute("urlMapper", this.projEnvUrlProperties.getMapper());
		model.addAttribute("urlSbs01", this.projEnvUrlProperties.getLns01());
		return "web/apis/list";
	}
	
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
