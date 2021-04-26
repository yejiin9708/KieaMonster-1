package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;
import org.tain.working.async.AsyncWorking;
import org.tain.working.ws.WsClientWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Working {

	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (!Flag.flag) jobForSimpleClient();
		if (Flag.flag) jobForAsyncWorking();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private WsClientWorking wsClientWorking;
	
	private void jobForSimpleClient() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.wsClientWorking.test();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private AsyncWorking asyncWorking;
	
	private void jobForAsyncWorking() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.asyncWorking.test01();
		
		for (int i=0; i < 100; i++) {
			Sleep.run(7 * 1000);
			if (Flag.flag) this.asyncWorking.test02();
		}
	}
}