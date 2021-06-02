package org.tain.mybatis.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CdItmMapper {

	// cdItm
	List<Map<String,Object>> selectAll(Map<String,Object> map);
	Map<String,Object> selectOne(Map<String,Object> map);
	
	// cdMst
	List<Map<String,Object>> selectAllByMst(Map<String,Object> map);
	Map<String,Object> selectOneByMst(Map<String,Object> map);
}
