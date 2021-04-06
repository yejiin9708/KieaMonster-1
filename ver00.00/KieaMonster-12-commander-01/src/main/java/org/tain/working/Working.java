package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.properties.ProjEnvBaseProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.working.load.TbCmdWorking;
import org.tain.working.load.TbGrpWorking;
import org.tain.working.load.TbOrgWorking;
import org.tain.working.load.TbSvrWorking;
import org.tain.working.properties.PropertiesWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Working {

	@Autowired
	private ProjEnvBaseProperties projEnvBaseProperties;
	
	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) jobForProperties();
		if (Flag.flag) jobForTbOrgLoad();
		if (Flag.flag) jobForTbGrpLoad();
		if (Flag.flag) jobForTbSvrLoad();
		if (Flag.flag) jobForTbCmdLoad();
		
		// whether to exit
		if (this.projEnvBaseProperties.isTestFlag()) System.exit(0);
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
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbGrpWorking tbGrpWorking;
	
	private void jobForTbGrpLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.tbGrpWorking.load();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbSvrWorking tbSvrWorking;
	
	private void jobForTbSvrLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.tbSvrWorking.load();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbCmdWorking tbCmdWorking;
	
	private void jobForTbCmdLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.tbCmdWorking.load();
	}
}