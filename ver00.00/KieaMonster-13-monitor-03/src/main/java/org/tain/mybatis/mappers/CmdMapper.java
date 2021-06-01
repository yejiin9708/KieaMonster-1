package org.tain.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.tain.mybatis.models.Cmd;

@Mapper
public interface CmdMapper {

	List<Cmd> selectAll();
	Cmd selectOne(Long id);
}
