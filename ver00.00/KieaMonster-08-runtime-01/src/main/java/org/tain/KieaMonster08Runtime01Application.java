package org.tain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.working.MonProcess;

@SpringBootApplication
public class KieaMonster08Runtime01Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaMonster08Runtime01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		String[] command = new String[] { "echo", ">>>>>>>>>>>>> Hello.. Kiea" };
		new MonProcess().execute(command);
		System.exit(0);
	}
}
