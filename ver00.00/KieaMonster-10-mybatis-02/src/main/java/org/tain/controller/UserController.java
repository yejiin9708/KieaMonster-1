package org.tain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.mappers.UserMapper;
import org.tain.models.User;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping(value = {"", "/list"})
	public List<User> list() {
		return this.userMapper.selectAllUsers();
	}
	
	@GetMapping("/{id}")
	public List<User> user(@PathVariable("id") Long id) {
		return this.userMapper.selectAllUsers(id);
	}
}
