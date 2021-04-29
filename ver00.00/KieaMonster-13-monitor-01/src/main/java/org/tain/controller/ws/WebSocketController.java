package org.tain.controller.ws;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/ws"})
@Slf4j
public class WebSocketController {

	@RequestMapping(value = {"/first"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String first() throws Exception {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		
		return "/ws/first";
	}
}
