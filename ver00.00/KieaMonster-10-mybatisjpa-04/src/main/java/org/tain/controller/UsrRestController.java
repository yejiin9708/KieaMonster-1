package org.tain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tain.mybatis.mappers.UsrMapper;

@RestController
@RequestMapping("/usr")
public class UsrRestController {

	@Autowired
	private UsrMapper usrMapper;
	
	@GetMapping({"", "/list"})
	public List<Map<String,Object>> list() {
		return this.usrMapper.selectAll(null);
	}
	
	@GetMapping("/{id}")
	public List<Map<String,Object>> selectById(@PathVariable("id") Long id) {
		Map<String,Object> mapIn = new HashMap<>();
		mapIn.put("id", id);
		return this.usrMapper.selectAll(mapIn);
	}
	
	@GetMapping("/{id}/delete")
	public int deleteById(@PathVariable("id") Long id) {
		Map<String,Object> mapIn = new HashMap<>();
		mapIn.put("id", id);
		return this.usrMapper.delete(mapIn);
	}
}
