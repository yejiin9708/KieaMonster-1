package org.tain.mybatis.models;

import lombok.Data;

@Data
public class Brw {

	private Long id;
	
	private String ipAddr;
	
	private String userId;
	
	private String createDate;
	
	private String content;  // dummy
}
