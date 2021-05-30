package org.tain.controller.mybatis;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tain.mybatis.mappers.UsrMapper;
import org.tain.mybatis.models.Usr;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping({"/usr"})
@Slf4j
public class UsrController {

	@Autowired
	private UsrMapper usrMapper;
	
	@GetMapping({"/list"})
	public List<Usr> list() {
		if (Boolean.TRUE) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null)
				ip = request.getRemoteAddr();
			log.info(">>>>> Client IP: " + ip);
		}
		
		return this.usrMapper.selectAllUsr();
	}
	
	@GetMapping({"/list/{id}"})
	public ResponseEntity<?> listById(@PathVariable("id") Long id, HttpEntity<String> httpEntity) {
		if (Boolean.TRUE) {
			HttpHeaders headers = httpEntity.getHeaders();
			String body = httpEntity.getBody();
			log.info(">>>>> request.headers: " + headers.toString());
			log.info(">>>>> request.body: " + body);
		}
		if (Boolean.TRUE) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			log.info(">>>>> X-FORWARDED-FOR: " + request.getHeader("X-FORWARDED-FOR"));
			log.info(">>>>> RemoteAddr: " + request.getRemoteAddr());
			
			String ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null)
				ip = request.getRemoteAddr();
			log.info(">>>>> Client IP: " + ip);
		}
		
		List<Usr> list = null;
		MultiValueMap<String,String> headers = null;
		if (Boolean.TRUE) {
			list = this.usrMapper.selectAllUsr(id);
			
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		return new ResponseEntity<>(list, headers, HttpStatus.OK);
	}
}
