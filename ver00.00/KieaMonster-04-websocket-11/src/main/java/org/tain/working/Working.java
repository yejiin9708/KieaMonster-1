package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;
import org.tain.working.async.AsyncClientWorking;
import org.tain.working.async.AsyncServerWorking;
import org.tain.working.ws.WsClientWorking;
import org.tain.working.ws.WsServerWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Working {

	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		// server
		if (Flag.flag) {
			if (Flag.flag) jobForAsyncServer();
			if (!Flag.flag) jobForWsServer();
		}
		
		// client
		if (!Flag.flag) {
			if (Flag.flag) jobForAsyncClient();
			if (!Flag.flag) jobForWsClient();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private AsyncServerWorking asyncServerWorking;
	
	private void jobForAsyncServer() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.asyncServerWorking.test00();  // create thread
		
		if (Flag.flag) Sleep.run(10 * 1000);
		if (Flag.flag) this.asyncServerWorking.test01();  // send messages
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private WsServerWorking wsServerWorking;
	
	private void jobForWsServer() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.wsServerWorking.test00();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private AsyncClientWorking asyncClientWorking;
	
	private void jobForAsyncClient() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.asyncClientWorking.test00();  // create thread
		
		if (Flag.flag) Sleep.run(10 * 1000);
		if (Flag.flag) this.asyncClientWorking.test01();  // send messages
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private WsClientWorking wsClientWorking;
	
	private void jobForWsClient() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.wsClientWorking.test00();
	}
}