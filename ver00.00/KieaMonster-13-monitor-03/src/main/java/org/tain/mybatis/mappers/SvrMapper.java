package org.tain.mybatis.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SvrMapper {

	// svr
	List<Map<String,Object>> selectAll(Map<String,Object> map);
	Map<String,Object> selectOne(Map<String,Object> map);
	
	// grp
	List<Map<String,Object>> selectAllByGrp(Map<String,Object> map);
	Map<String,Object> selectOneByGrp(Map<String,Object> map);
}
