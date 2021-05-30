package org.tain.mybatis.models;

import lombok.Data;

@Data
public class Usr {

	private Long id;
	
	private String userId;
	
	private String passWd;
	
	private String desc;
	
	private String comment;
}
