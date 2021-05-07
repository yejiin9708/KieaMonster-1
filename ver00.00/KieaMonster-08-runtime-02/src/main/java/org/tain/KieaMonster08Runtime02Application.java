package org.tain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.working.Working;

@SpringBootApplication
public class KieaMonster08Runtime02Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaMonster08Runtime02Application.class, args);
	}
	
	@Autowired
	private Working working;

	@Override
	public void run(String... args) throws Exception {
		try {
			this.working.work();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}

}
