package org.tain;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KieaMonster09Async02Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaMonster09Async02Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20210405 >>>>> Hello, world!!! {} {}", CurrentInfo.get());
	}
}
