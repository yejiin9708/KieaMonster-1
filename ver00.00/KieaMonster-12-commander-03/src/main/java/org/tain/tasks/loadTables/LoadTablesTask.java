package org.tain.tasks.loadTables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component("LoadTablesTask")
@Slf4j
public class LoadTablesTask {

	@Bean
	public void startLoaderTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of LoadTablesTask.");
		
		if (Boolean.TRUE) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	// loaderTask
	@Async(value = "async_0103")
	public void async0103(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0103 START {} {}", param, CurrentInfo.get());
	
		if (Boolean.TRUE) {
			if (Boolean.TRUE) jobForTbOrgLoad();
			if (Boolean.TRUE) jobForTbGrpLoad();
			if (Boolean.TRUE) jobForTbSvrLoad();
			if (Boolean.TRUE) jobForTbCmdLoad();
			if (Boolean.TRUE) jobForTbResultLoad();
			if (Boolean.TRUE) jobForTbUserLoad();
		}
		
		log.info("KANG-20200721 >>>>> async_0103 END   {} {}", param, CurrentInfo.get());
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbOrgWorking tbOrgWorking;
	
	private void jobForTbOrgLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.tbOrgWorking.load();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbGrpWorking tbGrpWorking;
	
	private void jobForTbGrpLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.tbGrpWorking.load();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbSvrWorking tbSvrWorking;
	
	private void jobForTbSvrLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.tbSvrWorking.load();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbCmdWorking tbCmdWorking;
	
	private void jobForTbCmdLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.tbCmdWorking.load();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbResultWorking tbResultWorking;
	
	private void jobForTbResultLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.tbResultWorking.load();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbUserWorking tbUserWorking;
	
	private void jobForTbUserLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.tbUserWorking.load();
	}
}