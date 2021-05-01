package org.tain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KieaMonster09Async02Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaMonster09Async02Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello, world!!!");
	}

}
