package org.tain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tain.mappers.UserMapper;
import org.tain.models.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public List<User> getAllUsers() {
		return this.userMapper.selectAllUsers();
	}
}
