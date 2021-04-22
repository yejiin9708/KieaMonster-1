package org.tain.server.component;

import org.springframework.stereotype.Component;

@Component
public class TestComponent {

	public void print(String message) throws Exception {
		System.out.println(">>>>> SERVER TestComponent.print: " + message);
	}
}
