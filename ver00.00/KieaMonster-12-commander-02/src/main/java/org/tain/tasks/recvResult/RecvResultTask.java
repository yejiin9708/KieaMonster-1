package org.tain.tasks.recvResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.db.domain.TbResult;
import org.tain.db.repository.TbResultRepository;
import org.tain.tasks.sendResult.SendResultTask;
import org.tain.tools.queue.MonQueue;
import org.tain.utils.CurrentInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component("RecvResultTask")
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
	
	private MonQueue<String> queue = new MonQueue<>();
	
	public void setQueue(String object) {
		this.queue.set(object);
	}
	
	public String getQueue() {
		return this.queue.get();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbResultRepository tbResultRepository;
	
	@Autowired
	private SendResultTask sendResultTask;
	
	// recvResult
	@Async(value = "async_0101")
	public void async0101(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async_0101 START {} {}", param, CurrentInfo.get());
	
		if (Boolean.TRUE) {
			while (true) {
				// get result from the queueLoadResult
				String msg = (String) this.getQueue();
				System.out.println(">>>>> 1. async " + param + ": " + msg);
				
				//////////////////////////////////////////////
				if (Boolean.TRUE) {
					// load result to tbResult
					// table insert
					TbResult result = new ObjectMapper().readValue(msg, TbResult.class);
					this.tbResultRepository.save(result);
				}
				
				// set result to the queueSendResult
				this.sendResultTask.setQueue(msg);
			}
		}
	}
}
