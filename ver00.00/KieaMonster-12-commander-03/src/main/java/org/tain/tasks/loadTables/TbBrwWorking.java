package org.tain.tasks.loadTables;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.db.domain.TbBrw;
import org.tain.db.repository.TbBrwRepository;
import org.tain.tools.properties.ProjEnvJsonProperties;
import org.tain.tools.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.StringTools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TbBrwWorking {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;

	@Autowired
	private ProjEnvJsonProperties projEnvJsonProperties;

	@Autowired
	private TbBrwRepository tbBrwRepository;
	
	public void load() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			// delete all
			this.tbBrwRepository.deleteAll();
		}
		
		if (Boolean.TRUE) {
			String filePath = this.projEnvParamProperties.getHome()
					+ this.projEnvParamProperties.getBase()
					+ this.projEnvParamProperties.getInfoPath()
					+ File.separator
					+ this.projEnvJsonProperties.getBrwInfoFile();
			if (Boolean.TRUE) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), filePath);

			String strJson = StringTools.stringFromFile(filePath);
			if (Boolean.TRUE) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), strJson);
			
			List<TbBrw> lstTbBrw = new ObjectMapper().readValue(strJson, new TypeReference<List<TbBrw>>() {});
			lstTbBrw.forEach(entry -> {
				if (Boolean.TRUE) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), entry);
				this.tbBrwRepository.save(entry);
			});
		}
	}
}
