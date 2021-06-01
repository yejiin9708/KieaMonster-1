package org.tain.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.tain.mybatis.models.Brw;

@Mapper
public interface BrwMapper {

	List<Brw> selectAll();
	Brw selectOne(Long id);
}
