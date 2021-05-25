package org.tain.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {

	@Select("select now() now1")
	public String getTime();
	
	public String getTimeXML();
}
