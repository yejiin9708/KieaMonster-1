package org.tain.tools.queue;

import org.springframework.stereotype.Component;
import org.tain.tools.node.MonJsonNode;

@Component("MonQueueBox")
public class MonQueueBox {

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	// queue for
	private MonQueue<MonJsonNode> queue = new MonQueue<>();
	
	public void setQueue(MonJsonNode object) {
		this.queue.set(object);
	}
	
	public MonJsonNode getQueue() {
		return this.queue.get();
	}
}
