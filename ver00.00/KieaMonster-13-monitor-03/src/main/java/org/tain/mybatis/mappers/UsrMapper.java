package org.tain.mybatis.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsrMapper {

	// usr
	List<Map<String,Object>> selectAll(Map<String,Object> map);
	Map<String,Object> selectOne(Map<String,Object> map);
}
