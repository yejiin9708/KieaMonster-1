package org.tain.mybatis.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.tain.mybatis.models.Org;

@Mapper
public interface OrgMapper {

	Org selectOne();
}
