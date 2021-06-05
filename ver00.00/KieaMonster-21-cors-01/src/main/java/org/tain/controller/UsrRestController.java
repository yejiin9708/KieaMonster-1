package org.tain.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
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
	@GetMapping({"/insert"})
	public int insertOne() {
		Map<String,Object> mapIn = null;
		if (Boolean.TRUE) {
			mapIn = new HashMap<>();
			mapIn.put("userId", "KANG");
			mapIn.put("passWd", "SEOK");
			mapIn.put("desc", "Time-" + LocalDateTime.now().toString());
		}
		return this.usrMapper.insertOne(mapIn);
	}
}
