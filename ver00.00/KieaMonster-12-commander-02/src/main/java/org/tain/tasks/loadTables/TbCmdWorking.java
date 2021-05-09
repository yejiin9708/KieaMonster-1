package org.tain.tasks.loadTables;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.db.domain.TbCmd;
import org.tain.db.repository.TbCmdRepository;
import org.tain.tools.properties.ProjEnvJsonProperties;
import org.tain.tools.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;
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
		
		if (Boolean.TRUE) {
			// delete all
			this.tbCmdRepository.deleteAll();
		}
		
		if (Boolean.TRUE) {
			String pathName = this.projEnvParamProperties.getHome()
					+ this.projEnvParamProperties.getBase()
					+ this.projEnvParamProperties.getInfoPath();
			String fileName = this.projEnvJsonProperties.getCmdInfoFile();
			if (Boolean.TRUE) log.info("KANG-20210406 >>>>> {} {}", fileName, pathName);
			
			File[] files = new File(pathName).listFiles((FileFilter) new WildcardFileFilter(fileName));
			for (File file : files) {
				if (Boolean.TRUE) log.info("KANG-20210406 >>>>> {} {} {}", CurrentInfo.get(), file.getAbsolutePath(), file.getName());
				
				String svrCode = getSvrCode(file.getName());
				String strJson = StringTools.stringFromFile(file.getAbsolutePath());
				if (Boolean.TRUE) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), strJson);
				
				List<TbCmd> lstTbCmd = new ObjectMapper().readValue(strJson, new TypeReference<List<TbCmd>>() {});
				lstTbCmd.forEach(entry -> {
					entry.setSvrCode  (svrCode);
					entry.setCmdCode  (entry.getCmdCode  ().trim());
					entry.setCmdName  (entry.getCmdName  ().trim());
					entry.setCmdDesc  (entry.getCmdDesc  ().trim());
					entry.setCmdPeriod(entry.getCmdPeriod().trim());
					entry.setCmdType  (entry.getCmdType  ().trim());
					entry.setCmdArr   (entry.getCmdArr   ().trim());
					if (Boolean.TRUE) log.info("KANG-20210406 >>>>> {} {}", CurrentInfo.get(), entry);
					this.tbCmdRepository.save(entry);
				});
			}
		}
	}
	
	private String getSvrCode(String fileName) throws Exception {
		int endPos = fileName.lastIndexOf('.');
		if (endPos < 5) {
			throw new Exception("KANG ERROR: parsing error [" + fileName + "]");
		}
		String svrCode = fileName.substring(4, endPos);
		return svrCode;
	}
}
