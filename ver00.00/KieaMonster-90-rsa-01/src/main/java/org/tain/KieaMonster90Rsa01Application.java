package org.tain;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.utils.CurrentInfo;
import org.tain.working.Working;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KieaMonster90Rsa01Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20210510 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaMonster90Rsa01Application.class, args);
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	@Autowired
	private Working working;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20210510 >>>>> {} {}", CurrentInfo.get());
		
		try {
			this.working.work();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (Boolean.TRUE) {
				System.out.println("\n=================== System.exit(0) =====================\n");
				System.exit(0);
			}
		}
	}

}
