package org.tain.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import org.tain.domain.Greeting;
import org.tain.domain.HelloMessage;

@Controller
public class GreetingController {

	@MessageMapping("/hello")      // client -> server.controller "/app/hello"
	@SendTo("/topic/greetings")    // server -> client.subscribe  "/topic/greetings"
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000);
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + " !");
	}
}
