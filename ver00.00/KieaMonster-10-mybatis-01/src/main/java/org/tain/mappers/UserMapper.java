package org.tain.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.tain.models.User;

@Mapper
public interface UserMapper {

	List<User> selectAllUsers();
}
