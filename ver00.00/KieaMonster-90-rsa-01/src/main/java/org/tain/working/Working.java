package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.working.rsatest.RSATestWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Working {

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private RSATestWorking rsaTestWorking;
	
	public void work() throws Exception {
		log.info("KANG-20210510 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.rsaTestWorking.test00();
			//this.rsaTestWorking.test01();
		}
	}
}
