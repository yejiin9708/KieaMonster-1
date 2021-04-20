package org.tain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * Model, ModelMap 공통점
 * 
 *     model.addAttribute("변수명");
 *     modelMap.addAttribute("변수명");
 *     둘 다 addAttribute를 사용함
 *     Model or ModelMap에 데이터만 저장 후 View에서 사용목적
 *  
 * 
 * Model, ModelMap 차이점
 * 
 *     Model - 인터페이스
 *     ModelMap - 클래스
 * 
 * ModelAndView
 * 
 *     addObject를 통해 데이터를 저장
 *     setViewName을 통해 이동하고자 하는 View를 저장
 *     메소드 안에서 ModelAndView mv = new ModelAndView(); 
 *     return type ModelAndView 
 */
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
