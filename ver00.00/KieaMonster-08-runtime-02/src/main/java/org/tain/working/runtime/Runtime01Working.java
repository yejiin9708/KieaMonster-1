package org.tain.working.runtime;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

@Component
public class Runtime01Working {

	public void test01() throws Exception {
		{
			String[] cmd = new String[] { "ping", "google.com" };
			Process process = new ProcessBuilder(cmd).start();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
			String line = null;
			//while ((line = br.readLine()) != null) {
			//	System.out.println("> " + line);
			//}
			for (int i=0; i < 10 && (line = br.readLine()) != null; i++) {
				System.out.println(i + "> " + line);
			}
			br.close();
			
			process.waitFor();
			System.out.println("> exit value = " + process.exitValue());
			process.destroy();
		}
	}
	
	public void test02() throws Exception {
		{
			
		}
	}
}
