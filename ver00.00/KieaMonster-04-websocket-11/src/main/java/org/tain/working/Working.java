package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;
import org.tain.working.async.AsyncWorking;
import org.tain.working.ws.WsServerWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Working {

	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (!Flag.flag) jobForSimpleServer();
		if (Flag.flag) jobForAsyncWorking();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private WsServerWorking wsServerWorking;
	
	private void jobForSimpleServer() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.wsServerWorking.test();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private AsyncWorking asyncWorking;
	
	private void jobForAsyncWorking() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.asyncWorking.test01();
		
		for (int i=0; i < 100; i++) {
			Sleep.run(10 * 1000);
			if (Flag.flag) this.asyncWorking.test02();
		}
	}
}