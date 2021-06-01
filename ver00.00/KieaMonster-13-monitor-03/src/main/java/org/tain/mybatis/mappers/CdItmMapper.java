package org.tain.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.tain.mybatis.models.CdItm;

@Mapper
public interface CdItmMapper {

	List<CdItm> selectAll();
	CdItm selectOne(Long id);
}
