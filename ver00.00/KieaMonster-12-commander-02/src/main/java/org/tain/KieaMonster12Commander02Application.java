package org.tain;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.tain.tools.properties.ProjEnvBaseProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Sleep;
import org.tain.working.Working;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAsync
@Slf4j
public class KieaMonster12Commander02Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaMonster12Commander02Application.class, args);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ProjEnvBaseProperties projEnvBaseProperties;
	
	@Autowired
	private Working working;

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		try {
			if (Boolean.TRUE) this.working.work();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (Boolean.TRUE && this.projEnvBaseProperties.isTestFlag()) {
				Sleep.run(5 * 1000);
				System.out.println("\n==========================  SYSTEM EXIT by TestFlag  ===========================\n");
				System.exit(0);
			}
		}
	}
}
