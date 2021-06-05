package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.working.loadUsr.LoadUsrWorking;

@Component
public class Working {

	public void work() throws Exception {
		if (Boolean.TRUE) {
			if (Boolean.TRUE) this.job01();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private LoadUsrWorking loadUsrWorking;
	
	private void job01() throws Exception {
		if (Boolean.TRUE) {
			if (Boolean.TRUE) {
				try {
					this.loadUsrWorking.delete();
				} catch (Exception e) {
					throw e;
				}
			}
		}
		
		if (Boolean.TRUE) {
			try {
				this.loadUsrWorking.load();
			} catch (Exception e) {
				throw e;
			}
		}
		
		if (Boolean.TRUE) {
			try {
				this.loadUsrWorking.loadFromJson();
			} catch (Exception e) {
				throw e;
			}
		}
	}
}
