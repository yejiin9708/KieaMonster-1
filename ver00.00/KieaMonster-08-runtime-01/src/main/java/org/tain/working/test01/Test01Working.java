package org.tain.working.test01;

import java.lang.ProcessBuilder.Redirect;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.stereotype.Component;
import org.tain.util.ProcessUtils;

@Component
public class Test01Working {

	/*
	 * method-1
	 */
	public void jobForRuntime() throws Exception {
		String[] command = new String[] { "echo", ">>>>>>>>>>> Hello, world!!!" };
		
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(command);
		//Process process = Runtime.getRuntime().exec(command);
		
		ProcessUtils.printStream(process);
	}
	
	/*
	 * method-2
	 */
	public void jobForProcessBuilder() throws Exception {
		String[] command = new String[] { "echo", ">>>>>>>>>>> Hello, world!!!" };
		
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		
		ProcessUtils.printStream(process);
	}
	
	/*
	 * method-3
	 */
	public void jobForCommandsExec() throws Exception {
		String[] command = new String[] { "echo", ">>>>>>>>>>> Hello, world!!!" };
		
		DefaultExecutor executor = new DefaultExecutor();
		int size = command.length;
		
		CommandLine commandLine = CommandLine.parse(command[0]);
		for (int i=1; i < size; i++) {
			commandLine.addArgument(command[i]);
		}
		executor.execute(commandLine);
	}
	
	/*
	 * method-4
	 */
	public void jobForProcessBuilderRedirect() throws Exception {
		String[] command = new String[] { "echo", ">>>>>>>>>>> Hello, world!!!" };
		
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.redirectOutput(Redirect.INHERIT);
		builder.redirectError(Redirect.INHERIT);
		
		builder.start();
	}
}
