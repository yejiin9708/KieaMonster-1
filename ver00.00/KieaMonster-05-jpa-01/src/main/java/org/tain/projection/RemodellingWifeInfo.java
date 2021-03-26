package org.tain.projection;

import org.springframework.beans.factory.annotation.Value;

public interface RemodellingWifeInfo {

	@Value("{#{target.firstName} #{target.lastName}}")
	String getName();
	
	int getAge();
	
	@Value("{#{target.husband.firstName} #{target.husband.lastName}}")
	String getHusband();
}
