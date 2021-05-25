package org.tain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.mappers.UserMapper;
import org.tain.models.User;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("")
	public List<User> index() {
		List<User> lstUser = this.userMapper.selectAllUsers();
		return lstUser;
	}
}
