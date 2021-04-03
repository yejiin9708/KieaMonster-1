package org.tain.working;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

public class MonProcess {

	public MonProcess() {}
	
	public void execute(String[] command) throws IOException, InterruptedException {
		
		// method-1
		this.byRuntime(command);
		
		// method-2
		this.byProcessBuilder(command);
		
		// method-3
		this.byCommonsExec(command);
		
		// method-4
		this.byProcessBuilderRedirect(command);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void byRuntime(String[] command) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(command);
		//Process process = Runtime.getRuntime().exec(command);
		printStream(process);
	}
	
	private void byProcessBuilder(String[] command) throws IOException, InterruptedException {
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		printStream(process);
	}
	
	private void byCommonsExec(String[] command) throws IOException, InterruptedException {
		DefaultExecutor executor = new DefaultExecutor();
		int size = command.length;
		
		CommandLine commandLine = CommandLine.parse(command[0]);
		for (int i=1; i < size; i++) {
			commandLine.addArgument(command[i]);
		}
		executor.execute(commandLine);
	}
	
	private void byProcessBuilderRedirect(String[] command) throws IOException, InterruptedException {
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.redirectOutput(Redirect.INHERIT);
		builder.redirectError(Redirect.INHERIT);
		builder.start();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void printStream(Process process) throws IOException, InterruptedException {
		process.waitFor();
		try (InputStream psout = process.getInputStream()) {
			copy(psout, System.out);
		}
	}
	
	private void copy(InputStream is, OutputStream os) throws IOException {
		try { Thread.sleep(5000); } catch (InterruptedException i) {}
		
		byte[] buffer = new byte[1024];
		int n = 0;
		while ((n = is.read(buffer)) != -1) {
			os.write(buffer, 0, n);
		}
	}
}
