package org.tain.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tain.jpa.domain.TbUser;
import org.tain.jpa.repository.TbUserRepository;
import org.tain.mybatis.mappers.UserMapper;
import org.tain.mybatis.models.User;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping(value = {"", "/list1"})
	public List<User> list1() {
		if (Boolean.TRUE) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null)
				ip = request.getRemoteAddr();
			System.out.println(">>>>> Client IP: " + ip);
		}
		return this.userMapper.selectAllUsers();
	}
	
	@GetMapping(value = {"/{id}"})
	public List<User> user(@PathVariable("id") Long id) {
		if (Boolean.TRUE) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null)
				ip = request.getRemoteAddr();
			System.out.println(">>>>> Client IP: " + ip);
		}
		return this.userMapper.selectAllUsers(id);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TbUserRepository tbUserRepository;
	
	@GetMapping(value = {"/list2"})
	public List<TbUser> list2() {
		if (Boolean.TRUE) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null)
				ip = request.getRemoteAddr();
			System.out.println(">>>>> Client IP: " + ip);
		}
		return this.tbUserRepository.findAll();
	}
}
