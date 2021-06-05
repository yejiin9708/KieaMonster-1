package org.tain.mybatis.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsrMapper {

	// selectAll
	List<Map<String,Object>> selectAll(Map<String,Object> mapIn);

	// insert
	@Insert("insert into tb_usr(id, user_id, pass_wd, desc) values (#{id}, #{userId}, #{passWd}, #{desc})")
	int insert(Map<String,Object> mapIn);
	
	// delete
	@Delete("delete from tb_usr where id = #{id}")
	int delete(Map<String,Object> mapIn);
}
