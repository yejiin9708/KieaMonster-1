package org.tain.tasks.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.queue.ObjectQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskSample {

	private ObjectQueue queue = new ObjectQueue();
	
	public void setObject(Object object) {
		this.queue.set(object);
	}
	
	public Object getObject() {
		return this.queue.get();
	}
	
	/////////////////////////////////////////////////////////////
	
	@Bean
	public void startTaskSample() throws Exception {
		log.info("KANG-20210405 >>>>> START {} {}", CurrentInfo.get());
		
		if (!Flag.flag) {
			/*
			// job for other component
			for (int i=0; i < 100; i++) {
				this.setObject(String.format("%03d", i));
				Sleep.run(1 * 1000);
			}
			*/
		}
		
		log.info("KANG-20210405 >>>>> END   {} {}", CurrentInfo.get());
	}
	
	/////////////////////////////////////////////////////////////
	
	@Async(value = "async_0101")
	public void async0101(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async01 START {} {} {}", param, CurrentInfo.get());
	
		if (Flag.flag) {
			while (true) {
				String msg = (String) this.getObject();
				System.out.println(">>>>> async " + param + ": " + msg);
			}
		}
		
		log.info("KANG-20200721 >>>>> async01 END   {} {} {}", param, CurrentInfo.get());
	}
	
	/////////////////////////////////////////////////////////////
	@Async(value = "async_0102")
	public void async0102(String param) throws Exception {
		log.info("KANG-20200721 >>>>> async02 START {} {} {}", param, CurrentInfo.get());
		
		Sleep.run(10 * 1000);
		
		log.info("KANG-20200721 >>>>> async02 END   {} {} {}", param, CurrentInfo.get());
	}
}
