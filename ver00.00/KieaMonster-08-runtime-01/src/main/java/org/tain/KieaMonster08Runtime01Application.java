package org.tain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.util.Flag;
import org.tain.working.Working;

@SpringBootApplication
public class KieaMonster08Runtime01Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaMonster08Runtime01Application.class, args);
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private Working working;

	@Override
	public void run(String... args) throws Exception {
		
		if (Flag.flag) this.working.work();
		
		System.exit(0);
	}
}
