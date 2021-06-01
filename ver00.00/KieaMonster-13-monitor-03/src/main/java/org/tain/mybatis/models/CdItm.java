package org.tain.mybatis.models;

import lombok.Data;

@Data
public class CdItm {

	private Long id;
	
	private String cdMst;
	
	private String cdItm;
	
	private String cdVal;
	
	private String desc;
	
	private String content;  // dummy
}
