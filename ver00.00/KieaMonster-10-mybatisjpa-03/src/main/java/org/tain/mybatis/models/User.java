package org.tain.mybatis.models;

import lombok.Data;

@Data
public class User {

	private Long id;
	
	private String userId;
	
	private String passWd;
	
	private String groupCd;
	
	private String rollCd;
	
	private String content;
}
