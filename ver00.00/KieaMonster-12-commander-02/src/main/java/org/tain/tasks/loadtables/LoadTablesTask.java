package org.tain.tasks.loadtables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component("LoadTablesTask")
@Slf4j
public class LoadTablesTask {

	@Bean
	public void startLoaderTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of LoadTablesTask.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	// loaderTask
	@Async(value = "async_0103")
	public void async0103(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0101 START {} {}", param, CurrentInfo.get());
	
		if (Flag.flag) {
			if (Flag.flag) jobForTbOrgLoad();
			if (Flag.flag) jobForTbGrpLoad();
			if (Flag.flag) jobForTbSvrLoad();
			if (Flag.flag) jobForTbCmdLoad();
			if (Flag.flag) jobForTbResultLoad();
		}
		
		log.info("KANG-20200721 >>>>> async_0101 END   {} {}", param, CurrentInfo.get());
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
}
