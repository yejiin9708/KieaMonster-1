package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.working.runtime.Runtime01Working;

@Component
public class Working {

	public void work() throws Exception {
		jobForRuntime01();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private Runtime01Working runtime01Working;
	
	public void jobForRuntime01() throws Exception {
		this.runtime01Working.test01();
	}
}
