package org.tain.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProcessUtils {

	public static void printStream(Process process) throws IOException, InterruptedException {
		process.waitFor();
		try (InputStream psout = process.getInputStream()) {
			copy(psout, System.out);
		}
	}
	
	public static void copy(InputStream is, OutputStream os) throws IOException {
		try { Thread.sleep(5000); } catch (InterruptedException i) {}
		
		byte[] buffer = new byte[1024];
		int n = 0;
		while ((n = is.read(buffer)) != -1) {
			os.write(buffer, 0, n);
		}
	}
}
