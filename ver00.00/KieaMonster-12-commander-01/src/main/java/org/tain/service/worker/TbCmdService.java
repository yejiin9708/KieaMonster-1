package org.tain.service.worker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tain.domain.TbCmd;
import org.tain.repository.TbCmdRepository;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TbCmdService {

	@Autowired
	private TbCmdRepository tbCmdRepository;
	
	public List<TbCmd> listBySvrCode(String svrCode) {
		log.info("KANG-20200803 >>>>> {} {}", CurrentInfo.get(), svrCode);
		return this.tbCmdRepository.findTbCmdListBySvrCode(svrCode);
	}
}
