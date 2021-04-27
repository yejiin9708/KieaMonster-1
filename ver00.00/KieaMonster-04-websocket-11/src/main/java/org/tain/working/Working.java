package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;
import org.tain.working.async.AsyncWorking;
import org.tain.working.ws.WsWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Working {

	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) jobForAsync();
		if (!Flag.flag) jobForWs();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private AsyncWorking asyncWorking;
	
	private void jobForAsync() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.asyncWorking.test00();  // create thread
		
		if (Flag.flag) Sleep.run(10 * 1000);
		if (Flag.flag) this.asyncWorking.test01();  // send messages
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private WsWorking wsWorking;
	
	private void jobForWs() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.wsWorking.test00();
	}
}