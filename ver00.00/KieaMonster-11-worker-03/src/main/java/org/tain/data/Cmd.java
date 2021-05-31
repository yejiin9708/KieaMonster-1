package org.tain.data;

import lombok.Data;

@Data
public class Cmd {

	private Long id;
	
	private String svrCode;
	
	private String cmdCode;
	
	private String cmdName;
	
	private String cmdDesc;
	
	private String cmdPeriod;
	
	private String cmdType;
	
	private String cmdArr;
}
