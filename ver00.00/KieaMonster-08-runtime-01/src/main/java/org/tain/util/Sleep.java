package org.tain.util;

public class Sleep {

	public static void run(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
		}
	}
}
