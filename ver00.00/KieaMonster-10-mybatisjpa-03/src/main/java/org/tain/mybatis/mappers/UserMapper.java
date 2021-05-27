package org.tain.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.tain.mybatis.models.User;

@Mapper
public interface UserMapper {

	List<User> selectAllUsers();
}
