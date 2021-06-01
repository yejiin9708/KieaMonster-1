package org.tain.mybatis.models;

import lombok.Data;

@Data
public class Org {

	private Long id;
	
	private String orgCode;
	
	private String orgName;
	
	private String orgDesc;
	
	private String orgComment;
	
	private String content;  // dummy
}
