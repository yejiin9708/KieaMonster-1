package org.tain.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.tain.mybatis.models.CdMst;

@Mapper
public interface CdMstMapper {

	List<CdMst> selectAll();
	CdMst selectOne(Long id);
}
