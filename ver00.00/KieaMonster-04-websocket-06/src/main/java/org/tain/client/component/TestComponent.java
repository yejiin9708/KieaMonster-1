package org.tain.client.component;

//@Component
public class TestComponent {

	public void print(String message) throws Exception {
		System.out.println(">>>>> CLIENT TestComponent.print: " + message);
	}
	
	public static void print2(String message) throws Exception {
		System.out.println(">>>>> CLIENT static TestComponent.print2: " + message);
	}
}
