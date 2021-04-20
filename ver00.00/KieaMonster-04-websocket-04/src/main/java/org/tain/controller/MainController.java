package org.tain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/1")
	public String index1() {
		return "index";
	}
	
	@RequestMapping("/2")
	public String index2(Model model) {
		model.addAttribute("name", "Kiea Seok");
		return "index";
	}
	
	@RequestMapping("/3")
	public String index3(ModelMap mapModel) {
		mapModel.addAttribute("name", "Kiea Seok");
		return "index";
	}
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index2");
		mav.setViewName("index");
		mav.addObject("name", "Kiea");
		return mav;
	}
	
}
