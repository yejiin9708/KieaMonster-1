package org.tain.working.seedtest;

import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SeedTestWorking {

	public void test00() throws Exception {
		log.info("KANG-20210510 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			
		}
	}
}
