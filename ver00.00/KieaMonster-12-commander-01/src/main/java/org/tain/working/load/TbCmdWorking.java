package org.tain.working.load;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.domain.TbCmd;
import org.tain.properties.ProjEnvJsonProperties;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.repository.TbCmdRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.StringTools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TbCmdWorking {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;

	@Autowired
	private ProjEnvJsonProperties projEnvJsonProperties;

	@Autowired
	private TbCmdRepository tbCmdRepository;
	
	public void load() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// delete all
			this.tbCmdRepository.deleteAll();
		}
		
		if (Flag.flag) {
			String pathName = this.projEnvParamProperties.getHome()
					+ this.projEnvParamProperties.getBase()
					+ this.projEnvParamProperties.getInfoPath();
			String fileName = this.projEnvJsonProperties.getCmdInfoFile();
			if (Flag.flag) log.info("KANG-20210406 >>>>> {} {}", fileName, pathName);
			
			File[] files = new File(pathName).listFiles((FileFilter) new WildcardFileFilter(fileName));
			for (File file : files) {
				if (Flag.flag) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), file.getAbsolutePath());
				
				String strJson = StringTools.stringFromFile(file.getAbsolutePath());
				if (Flag.flag) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), strJson);
				
				List<TbCmd> lstTbCmd = new ObjectMapper().readValue(strJson, new TypeReference<List<TbCmd>>() {});
				lstTbCmd.forEach(entry -> {
					if (Flag.flag) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), entry);
					this.tbCmdRepository.save(entry);
				});
			}
		}
	}
}
