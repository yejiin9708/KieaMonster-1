package org.tain.temp;

import org.springframework.stereotype.Component;

@Deprecated
@Component
public class SimplePrint {

	public void print(String message) {
		System.out.println("> " + message);
	}
}
