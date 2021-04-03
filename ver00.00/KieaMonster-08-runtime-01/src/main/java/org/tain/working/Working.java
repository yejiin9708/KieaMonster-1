package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.util.Flag;
import org.tain.working.test01.Test01Working;
import org.tain.working.test02.Test02Working;

@Component
public class Working {

	public void work() throws Exception {
		if (Flag.flag) job01();  // for test01
		if (Flag.flag) job02();  // for test02
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private Test01Working test01Working;
	
	private void job01() throws Exception {
		if (Flag.flag) this.test01Working.jobForRuntime();
		if (Flag.flag) this.test01Working.jobForProcessBuilder();
		if (Flag.flag) this.test01Working.jobForCommandsExec();
		if (Flag.flag) this.test01Working.jobForProcessBuilderRedirect();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private Test02Working test02Working;
	
	private void job02() throws Exception {
		if (Flag.flag) this.test02Working.jobFor();
	}
}