package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.working.commands.CommandsWorking;
import org.tain.working.properties.PropertiesWorking;
import org.tain.working.result.ResultWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Working {

	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) jobForProperties();
		if (Flag.flag) jobForCommands();
		if (Flag.flag) jobForResult();
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
	private CommandsWorking commandsWorking;
	
	private void jobForCommands() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.commandsWorking.test01();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ResultWorking resultWorking;
	
	private void jobForResult() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.resultWorking.test01();
	}
}