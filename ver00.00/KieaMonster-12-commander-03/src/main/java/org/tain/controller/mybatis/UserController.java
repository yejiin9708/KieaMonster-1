package org.tain.controller.mybatis;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tain.mybatis.mappers.UsrMapper;
import org.tain.mybatis.models.Usr;

@RestController
@RequestMapping({"/usr"})
public class UserController {

	@Autowired
	private UsrMapper usrMapper;
	
	@GetMapping({"/list"})
	public List<Usr> list() {
		if (Boolean.TRUE) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null)
				ip = request.getRemoteAddr();
			System.out.println(">>>>> Client IP: " + ip);
		}
		
		return this.usrMapper.selectAllUsr();
	}
	
	@GetMapping({"/list/{id}"})
	public List<Usr> listById(@PathVariable("id") Long id) {
		if (Boolean.TRUE) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null)
				ip = request.getRemoteAddr();
			System.out.println(">>>>> Client IP: " + ip);
		}
		
		return this.usrMapper.selectAllUsr(id);
	}
}
