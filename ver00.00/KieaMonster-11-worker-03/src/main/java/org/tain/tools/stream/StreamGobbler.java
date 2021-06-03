package org.tain.tools.stream;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * 
 * if (Boolean.TRUE) {
 *     // method-7
 *     //String cmd = "ps -ef | head";
 *     String cmd = "java -version";
 *     Process process = Runtime.getRuntime().exec(cmd);
 *     
 *     StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "ERROR");
 *     StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), "OUTPUT");
 *     errorGobbler.start();
 *     outputGobbler.start();
 *     
 *     int exitVal = process.waitFor();
 *     System.out.println("Process exitVal = " + exitVal);
 *     process.destroy();
 * }
 *
 */
public class StreamGobbler extends Thread {
	
	private String type = null;
	private InputStream is = null;
	private OutputStream os = null;
	
	public StreamGobbler(InputStream is, String type) {
		this(is, type, null);
	}
	
	public StreamGobbler(InputStream is, String type, OutputStream redirect) {
		this.is = is;
		this.type = type;
		this.os = redirect;
	}
	
	public void run() {
		try {
			PrintWriter pw = null;
			if (this.os != null)
				pw = new PrintWriter(this.os);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(this.is, "EUC-KR"));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (this.os != null)
					pw.println(line);
				System.out.println(type + "> " + line);
			}
			if (this.os != null) {
				pw.flush();
				//pw.close();
			}
			this.is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}