package org.tain.tasks.recvResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.db.domain.TbResult;
import org.tain.db.repository.TbResultRepository;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueueBox;
import org.tain.utils.CurrentInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component("RecvResultTask")
@DependsOn({"MonQueueBox"})
@Slf4j
public class RecvResultTask {

	@Bean
	public void startRecvResultTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of RecvResultTask.");
		
		if (Boolean.TRUE) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private MonQueueBox monQueueBox;
	
	@Autowired
	private TbResultRepository tbResultRepository;
	
	// recvResult
	@Async(value = "async_0101")
	public void async0101(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0101 START {} {}", param, CurrentInfo.get());
	
		if (Boolean.TRUE) {
			while (true) {
				// get result from the queueLoadResult
				MonJsonNode node = this.monQueueBox.getQueueRecvResult();
				System.out.println(">>>>> 1. async " + param + ": " + node.toPrettyString());
				
				//////////////////////////////////////////////
				if (Boolean.TRUE) {
					// set result to the queueSendResult
					this.monQueueBox.setQueueLinkMonitor(node);
				}
				
				//////////////////////////////////////////////
				if (Boolean.TRUE) {
					// load result to tbResult
					// table insert
					TbResult result = new ObjectMapper().readValue(node.toString(), TbResult.class);
					this.tbResultRepository.save(result);
				}
			}
		}
	}
}
