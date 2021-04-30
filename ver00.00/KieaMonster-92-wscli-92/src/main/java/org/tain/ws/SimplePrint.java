package org.tain.ws;

import org.springframework.stereotype.Component;

@Component
public class SimplePrint {

	public void print(String message) {
		System.out.println("> " + message);
	}
}
