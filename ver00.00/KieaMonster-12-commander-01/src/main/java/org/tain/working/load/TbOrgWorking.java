package org.tain.working.load;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.domain.TbOrg;
import org.tain.properties.ProjEnvJsonProperties;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.repository.TbOrgRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.StringTools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TbOrgWorking {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;

	@Autowired
	private ProjEnvJsonProperties projEnvJsonProperties;

	@Autowired
	private TbOrgRepository tbOrgRepository;
	
	public void load() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());

		if (Flag.flag) {
			// delete all
			this.tbOrgRepository.deleteAll();
		}
		
		if (Flag.flag) {
			String filePath = this.projEnvParamProperties.getHome()
					+ this.projEnvParamProperties.getBase()
					+ this.projEnvParamProperties.getInfoPath()
					+ File.separator
					+ this.projEnvJsonProperties.getOrgInfoFile();
			if (Flag.flag) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), filePath);

			String strJson = StringTools.stringFromFile(filePath);
			if (Flag.flag) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), strJson);
			
			List<TbOrg> lstTbOrg = new ObjectMapper().readValue(strJson, new TypeReference<List<TbOrg>>() {});
			lstTbOrg.forEach(entry -> {
				if (Flag.flag) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), entry);
				this.tbOrgRepository.save(entry);
			});
		}
	}
}
