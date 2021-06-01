package org.tain.mybatis.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrgMapper {

	// org
	Map<String,Object> selectOne(Map<String,Object> map);
}
