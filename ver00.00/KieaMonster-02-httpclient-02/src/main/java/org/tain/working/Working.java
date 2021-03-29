package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.working.tests.TestsWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Working {

	@Autowired
	private TestsWorking testsWorking;
	
	public void job01() throws Exception {
		log.info("KANG-20210320 >>>>> {} {}", CurrentInfo.get());
		
		//if (Flag.flag) this.testsWorking.jobTest01();
		//if (Flag.flag) this.testsWorking.jobTest02();
		
		if (!Flag.flag) this.testsWorking.jobTestGetPage();
		if (!Flag.flag) this.testsWorking.jobTestPost();
		if (!Flag.flag) this.testsWorking.jobTestGetSingle();
		if (!Flag.flag) this.testsWorking.jobTestPut();
		if (Flag.flag) this.testsWorking.jobTestDelete();
	}
}
