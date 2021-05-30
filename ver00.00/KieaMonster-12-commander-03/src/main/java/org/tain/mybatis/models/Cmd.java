package org.tain.mybatis.models;

import lombok.Data;

@Data
public class Cmd {

	private Long id;
	
	private String mstType;
	
	private String mstCode;
	
	private String cmdCode;
	
	private String cmdName;
	
	private String cmdDesc;
	
	private String cmdPeriod;
	
	private String cmdType;
	
	private String cmdArr;
}
