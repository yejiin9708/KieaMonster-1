package org.tain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.mybatis.mappers.UsrMapper;

@RestController
@RequestMapping("/usr")
public class UsrRestController {

	@Autowired
	private UsrMapper usrMapper;
	
	@CrossOrigin(origins="*", methods = {RequestMethod.GET, RequestMethod.POST}, maxAge = 3600)
	@GetMapping({"", "/list"})
	public List<Map<String,Object>> list() {
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
			mapIn = new HashMap<>();
		}
		return this.usrMapper.selectAll(mapIn);
	}
}
