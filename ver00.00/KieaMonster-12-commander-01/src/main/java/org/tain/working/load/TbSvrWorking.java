package org.tain.working.load;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.domain.TbSvr;
import org.tain.properties.ProjEnvJsonProperties;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.repository.TbSvrRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.StringTools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TbSvrWorking {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;

	@Autowired
	private ProjEnvJsonProperties projEnvJsonProperties;

	@Autowired
	private TbSvrRepository tbSvrRepository;
	
	public void load() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// delete all
			this.tbSvrRepository.deleteAll();
		}
		
		if (Flag.flag) {
			String filePath = this.projEnvParamProperties.getHome()
					+ this.projEnvParamProperties.getBase()
					+ this.projEnvParamProperties.getInfoPath()
					+ File.separator
					+ this.projEnvJsonProperties.getSvrInfoFile();
			if (Flag.flag) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), filePath);

			String strJson = StringTools.stringFromFile(filePath);
			if (Flag.flag) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), strJson);
			
			List<TbSvr> lstTbSvr = new ObjectMapper().readValue(strJson, new TypeReference<List<TbSvr>>() {});
			lstTbSvr.forEach(entry -> {
				if (Flag.flag) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), entry);
				this.tbSvrRepository.save(entry);
			});
		}
	}
}
