package org.tain.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tain.domain.board.Board;
import org.tain.repository.board.BoardRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public Page<Board> findBoardList(Pageable pageable) {
		if (Flag.flag) {
			int pageNumber = pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1;
			int pageSize = pageable.getPageSize();
			Sort sort = pageable.getSort();
			
			pageSize = 10;
			sort = Sort.by("id").descending().and(Sort.by("title").ascending()).and(Sort.by("userId").ascending());
			
			pageable = PageRequest.of(pageNumber, pageSize, sort);
		}
		log.info("KANG-20200803 >>>>> {} {}", CurrentInfo.get(), pageable);
		return this.boardRepository.findAll(pageable);
	}
	
	public Board findBoardById(Long id) {
		log.info("KANG-20200803 >>>>> {} {}", CurrentInfo.get());
		return this.boardRepository.findById(id).orElse(new Board());
	}
	
	public Page<Board> findBoardListByUserId(Pageable pageable, String userId) {
		if (Flag.flag) {
			int pageNumber = pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1;
			int pageSize = pageable.getPageSize();
			Sort sort = pageable.getSort();
			
			pageSize = 10;
			sort = Sort.by("id").descending().and(Sort.by("title").ascending()).and(Sort.by("userId").ascending());
			
			pageable = PageRequest.of(pageNumber, pageSize, sort);
		}
		log.info("KANG-20200803 >>>>> {} {}", CurrentInfo.get(), pageable);
		return this.boardRepository.findBoardListByUserId(pageable, userId);
	}
}
