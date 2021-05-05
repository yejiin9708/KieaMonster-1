package org.tain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tain.domain.TbResult;
import org.tain.repository.TbResultRepository;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TbResultService {

	@Autowired
	private TbResultRepository tbResultRepository;
	
	public List<TbResult> listBySvrCode(String svrCode) {
		log.info("KANG-20200803 >>>>> {} {}", CurrentInfo.get(), svrCode);
		return this.tbResultRepository.findTbResultListBySvrCode(svrCode);
	}
}
