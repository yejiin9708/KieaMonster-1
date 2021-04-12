package org.tain.working.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.node.MonJsonNode;
import org.tain.properties.ProjEnvBaseProperties;
import org.tain.properties.ProjEnvJobProperties;
import org.tain.properties.ProjEnvJsonProperties;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PropertiesWorking {

	@Autowired
	private ProjEnvBaseProperties projEnvBaseProperties;
	
	@Autowired
	private ProjEnvJobProperties projEnvJobProperties;
	
	@Autowired
	private ProjEnvJsonProperties projEnvJsonProperties;
	
	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	public void print() {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("KANG-20210405 >>>>> {} {}", "- BASE --", MonJsonNode.getPrettyJson(this.projEnvBaseProperties));
			log.info("KANG-20210405 >>>>> {} {}", "- PARAM -", MonJsonNode.getPrettyJson(this.projEnvParamProperties));
			log.info("KANG-20210405 >>>>> {} {}", "- JOB  --", MonJsonNode.getPrettyJson(this.projEnvJobProperties));
			log.info("KANG-20210405 >>>>> {} {}", "- JSON --", MonJsonNode.getPrettyJson(this.projEnvJsonProperties));
			log.info("KANG-20210405 >>>>> {} {}", "- URL  --", MonJsonNode.getPrettyJson(this.projEnvUrlProperties));
		}
	}
}
