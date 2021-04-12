package org.tain;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.properties.ProjEnvBaseProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.working.Working;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KieaMonster12Commander01Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaMonster12Commander01Application.class, args);
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
			if (Flag.flag) this.working.work();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.projEnvBaseProperties.isTestFlag()) {
				System.out.println("\n==========================  SYSTEM EXIT by TestFlag  ===========================\n");
				System.exit(0);
			}
		}
	}
}
