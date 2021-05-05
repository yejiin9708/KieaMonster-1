package org.tain.tasks.recvresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.domain.TbResult;
import org.tain.repository.TbResultRepository;
import org.tain.tasks.sendresult.SendResultTask;
import org.tain.tools.queue.MonQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component("RecvResultTask")
@Slf4j
public class RecvResultTask {

	@Autowired
	private TbResultRepository tbResultRepository;
	
	@Bean
	public void startRecvResultTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of RecvResultTask.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private MonQueue<Object> queueLoadResult = new MonQueue<>();
	
	public void setQueueLoadResult(Object object) {
		this.queueLoadResult.set(object);
	}
	
	public Object getQueueLoadResult() {
		return this.queueLoadResult.get();
	}
	
	@Autowired
	private SendResultTask sendResultTask;
	
	// recvResult
	@Async(value = "async_0101")
	public void async0101(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0101 START {} {}", param, CurrentInfo.get());
	
		if (Flag.flag) {
			while (true) {
				// get result from the queueLoadResult
				String msg = (String) this.getQueueLoadResult();
				System.out.println(">>>>> 1. async " + param + ": " + msg);
				
				//////////////////////////////////////////////
				if (Flag.flag) {
					// load result to tbResult
					// table insert
					TbResult result = new ObjectMapper().readValue(msg, TbResult.class);
					this.tbResultRepository.save(result);
				}
				
				// set result to the queueSendResult
				this.sendResultTask.setQueueSendResult(msg);
			}
		}
	}
}
