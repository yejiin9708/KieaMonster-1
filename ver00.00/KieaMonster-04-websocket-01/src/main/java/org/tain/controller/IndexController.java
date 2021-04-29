package org.tain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "/index.html";
	}
	
	@RequestMapping("/ws")
	public String websocket() {
		return "ws/websocket.html";
	}
	
	@RequestMapping("/test")
	public String test() {
		return "test/test.html";
	}
}
