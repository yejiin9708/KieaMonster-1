package org.tain.mybatis.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsrMapper {

	int insertOne(Map<String,Object> mapIn);
}
