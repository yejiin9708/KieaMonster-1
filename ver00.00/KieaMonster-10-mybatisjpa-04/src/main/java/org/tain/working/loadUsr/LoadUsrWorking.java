package org.tain.working.loadUsr;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.jpa.domain.TbUsr;
import org.tain.jpa.repository.TbUsrRepository;
import org.tain.mybatis.mappers.UsrMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoadUsrWorking {

	@Autowired
	private TbUsrRepository tbUsrRepository;
	
	@Autowired
	private UsrMapper usrMapper;
	
	public void delete() throws Exception {
		//if (Boolean.TRUE) this.tbUsrRepository.deleteAll();
		if (Boolean.TRUE) this.tbUsrRepository.deleteAllInBatch();
	}
	
	public void load() throws Exception {
		if (Boolean.TRUE) {
			IntStream.rangeClosed(1, 5).forEach(index -> {
				TbUsr tbUsr = this.tbUsrRepository.save(TbUsr.builder()
						.userId("usr" + index)
						.passWd("pass" + index)
						.desc("desc-" + index)
						.build());
				System.out.println(">>>> tbUsr: " + tbUsr);
			});
		}
	}
	
	public void loadFromJson() throws Exception {
		// {"userId":"kang","passWd":"seok","desc":"Hello world"}
		String strJson = "{\"userId\":\"kang\",\"passWd\":\"seok\",\"desc\":\"Hello world\"}";
		TbUsr temp = new ObjectMapper().readValue(strJson, TbUsr.class);
		
		if (!Boolean.TRUE) {
			TbUsr tbUsr = this.tbUsrRepository.save(TbUsr.builder()
					.userId(temp.getUserId())
					.passWd(temp.getPassWd())
					.desc(temp.getDesc())
					.build());
			System.out.println(">>>> tbUsr: " + tbUsr);
		}
		
		if (Boolean.TRUE) {
			Map<String,Object> mapIn = new HashMap<>();
			mapIn.put("userId", temp.getUserId());
			mapIn.put("passWd", temp.getPassWd());
			mapIn.put("desc", temp.getDesc());
			
			int ret = this.usrMapper.insert(mapIn);
			System.out.println(">>>> ret after insert: " + ret);
		}
	}
}
