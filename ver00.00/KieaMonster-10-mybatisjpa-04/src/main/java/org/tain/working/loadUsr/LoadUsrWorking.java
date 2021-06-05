package org.tain.working.loadUsr;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.jpa.domain.TbUsr;
import org.tain.jpa.repository.TbUsrRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoadUsrWorking {

	@Autowired
	private TbUsrRepository tbUsrRepository;
	
	public void delete() throws Exception {
		try {
			//if (Boolean.TRUE) this.tbUsrRepository.deleteAll();
			if (Boolean.TRUE) this.tbUsrRepository.deleteAllInBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void load() throws Exception {
		try {
			IntStream.rangeClosed(1, 5).forEach(index -> {
				TbUsr tbUsr = this.tbUsrRepository.save(TbUsr.builder()
						.userId("usr" + index)
						.passWd("pass" + index)
						.desc("desc-" + index)
						.build());
				System.out.println(">>>> tbUsr: " + tbUsr);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadFromJson() throws Exception {
		try {
			// {"userId":"kang","passWd":"seok","desc":"Hello world"}
			String strJson = "{\"userId\":\"kang\",\"passWd\":\"seok\",\"desc\":\"Hello world\"}";
			TbUsr temp = new ObjectMapper().readValue(strJson, TbUsr.class);
			
			TbUsr tbUsr = this.tbUsrRepository.save(TbUsr.builder()
					.userId(temp.getUserId())
					.passWd(temp.getPassWd())
					.desc(temp.getDesc())
					.build());
			System.out.println(">>>> tbUsr: " + tbUsr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
