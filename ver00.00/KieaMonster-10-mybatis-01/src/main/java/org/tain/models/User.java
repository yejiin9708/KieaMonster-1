package org.tain.models;

import lombok.Data;

@Data
public class User {

	private Long id;
	
	private String groupCd;
	
	private String userId;
	
	private String passWd;
	
	private String rollCd;
}
