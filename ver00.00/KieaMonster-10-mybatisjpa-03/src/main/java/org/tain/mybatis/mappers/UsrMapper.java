package org.tain.mybatis.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsrMapper {

	List<Map<String,Object>> selectAll(Map<String,Object> mapIn);
	
	@Insert("insert into tb_usr(id, user_id, pass_wd, desc) values (#{id}, #{userId}, #{passWd}, #{desc})")
	void createUsr(Map<String,Object> mapIn);
}
