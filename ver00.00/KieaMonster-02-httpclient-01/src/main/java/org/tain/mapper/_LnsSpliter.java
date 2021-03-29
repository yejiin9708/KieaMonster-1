package org.tain.mapper;

import lombok.Data;

@Deprecated
@Data
//@Slf4j
public class _LnsSpliter {

	private String pathName;
	private String fieldName;
	
	public _LnsSpliter(String name) {
		int idx = name.lastIndexOf('/');
		if (idx < 0) {
			this.pathName = name;
			this.fieldName = name;
		}
	}
	
	public _LnsSpliter(String pathName, String fieldName) {
		this.pathName = pathName;
		this.fieldName = fieldName;
	}
}
