package org.tain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.mybatis.mappers.UsrMapper;

@RestController
@RequestMapping("/usr")
public class UsrRestController {

	@Autowired
	private UsrMapper usrMapper;
	
	@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST}, maxAge = 3600)
	@GetMapping(value = {"", "/list"})
	public List<Map<String,Object>> list() {
		return this.usrMapper.selectAll(null);
	}
	
	@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST}, maxAge = 3600)
	@GetMapping(value = {"/{id}"})
	public List<Map<String,Object>> listById(@PathVariable("id") Long id) {
		Map<String,Object> mapIn = new HashMap<>();
		mapIn.put("id", id);
		return this.usrMapper.selectAll(mapIn);
	}
	
	@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST}, maxAge = 3600)
	@GetMapping(value = {"/{id}/create"})
	public int createUsr(@PathVariable("id") Long id) {
		Map<String,Object> mapIn = new HashMap<>();
		mapIn.put("id", id);
		mapIn.put("userId", "kang");
		mapIn.put("passWd", "seok");
		mapIn.put("desc", "Hello");
		this.usrMapper.createUsr(mapIn);
		return 0;
	}
}
