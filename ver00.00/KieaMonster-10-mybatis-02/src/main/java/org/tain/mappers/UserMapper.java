package org.tain.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import org.tain.models.User;

@Mapper
public interface UserMapper {

	@Transactional
	List<User> selectAllUsers();
	
	List<User> selectAllUsers(Long id);
}
