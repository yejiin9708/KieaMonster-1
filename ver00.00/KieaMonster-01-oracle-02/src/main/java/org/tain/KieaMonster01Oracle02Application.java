package org.tain;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.working.board.BoardWorking;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KieaMonster01Oracle02Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200803 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaMonster01Oracle02Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200803 >>>>> {} {}", CurrentInfo.get());
		if (!Flag.flag) job01();  // board
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private BoardWorking boardWorking;
	
	private void job01() {
		log.info("KANG-20200803 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.boardWorking.loading();
		if (Flag.flag) this.boardWorking.selecting();
		if (Flag.flag) this.boardWorking.commit();
	}
}
