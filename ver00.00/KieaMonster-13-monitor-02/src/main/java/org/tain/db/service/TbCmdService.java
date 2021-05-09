package org.tain.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tain.db.domain.TbCmd;
import org.tain.db.repository.TbCmdRepository;
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
	
	public Page<TbCmd> listAll(Pageable pageable) {
		if (Boolean.TRUE) {
			int pageNumber = pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1;
			int pageSize = pageable.getPageSize();
			Sort sort = pageable.getSort();
			
			pageSize = 20;
			//sort = Sort.by("id").ascending();
			//sort = Sort.by("id").descending().and(Sort.by("title").ascending()).and(Sort.by("userId").ascending());
			sort = Sort.by("svrCode").ascending().and(Sort.by("id").ascending());
			
			pageable = PageRequest.of(pageNumber, pageSize, sort);
		}
		log.info("KANG-20200803 >>>>> {} {}", pageable, CurrentInfo.get());
		return this.tbCmdRepository.findAll(pageable);
	}
	
	public TbCmd getOne(Long id) {
		log.info("KANG-20200803 >>>>> {} {}", CurrentInfo.get());
		return this.tbCmdRepository.getOne(id);
	}
	
}
