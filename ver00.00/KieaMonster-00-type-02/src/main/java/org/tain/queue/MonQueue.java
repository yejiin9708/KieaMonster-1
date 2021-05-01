package org.tain.queue;

import java.util.LinkedList;

import org.tain.node.MonJsonNode;

public class MonQueue {

	private final LinkedList<MonJsonNode> queue = new LinkedList<>();
	
	public synchronized void set(MonJsonNode object) {
		this.queue.addLast(object);
		this.notifyAll();
	}
	
	public synchronized MonJsonNode get() {
		while (this.queue.size() <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		return this.queue.removeFirst();
	}
	
	public synchronized void clear() {
		this.queue.clear();
	}
	
	public int size() {
		return this.queue.size();
	}
}
