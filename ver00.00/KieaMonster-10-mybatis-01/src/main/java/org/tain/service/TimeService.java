package org.tain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tain.mappers.TimeMapper;

@Service
public class TimeService {

	@Autowired
	private TimeMapper timeMapper;
	
	public String getNow1() {
		return this.timeMapper.getTime();
	}
	
	public String getNow2() {
		return this.timeMapper.getTimeXML();
	}
}
