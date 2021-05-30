package org.tain.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.tain.mybatis.models.Usr;

@Mapper
public interface UsrMapper {

	List<Usr> selectAllUsr();
	
	List<Usr> selectAllUsr(Long id);
}
