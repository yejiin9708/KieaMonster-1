package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;
import org.tain.working.async.AsyncServerWorking;
import org.tain.working.properties.PropertiesWorking;
import org.tain.working.tbload.TbCmdWorking;
import org.tain.working.tbload.TbGrpWorking;
import org.tain.working.tbload.TbOrgWorking;
import org.tain.working.tbload.TbResultWorking;
import org.tain.working.tbload.TbSvrWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Working {

	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		// properties
		if (Flag.flag) jobForProperties();
		
		// table loader
		if (Flag.flag) {
			if (Flag.flag) jobForTbOrgLoad();
			if (Flag.flag) jobForTbGrpLoad();
			if (Flag.flag) jobForTbSvrLoad();
			if (Flag.flag) jobForTbCmdLoad();
			if (Flag.flag) jobForTbResultLoad();
		}
		
		// websocket server
		if (Flag.flag) {
			if (Flag.flag) jobForAsyncServer();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private PropertiesWorking propertiesWorking;
	
	private void jobForProperties() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.propertiesWorking.print();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbOrgWorking tbOrgWorking;
	
	private void jobForTbOrgLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.tbOrgWorking.load();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbGrpWorking tbGrpWorking;
	
	private void jobForTbGrpLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.tbGrpWorking.load();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbSvrWorking tbSvrWorking;
	
	private void jobForTbSvrLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.tbSvrWorking.load();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbCmdWorking tbCmdWorking;
	
	private void jobForTbCmdLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.tbCmdWorking.load();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbResultWorking tbResultWorking;
	
	private void jobForTbResultLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.tbResultWorking.load();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private AsyncServerWorking asyncServerWorking;
	
	private void jobForAsyncServer() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.asyncServerWorking.test00();  // create thread
		
		if (!Flag.flag) Sleep.run(10 * 1000);
		if (!Flag.flag) this.asyncServerWorking.test01();  // send messages
	}
}
