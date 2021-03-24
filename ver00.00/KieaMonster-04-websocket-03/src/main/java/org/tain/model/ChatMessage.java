package org.tain.model;

public class ChatMessage {

	private MessageType type;
	private String content;
	private String sender;
	
	public enum MessageType {
		CHAT,
		JOIN,
		LEAVE
	}
	
	public void setType(MessageType type) {
		this.type = type;
	}
	
	public MessageType getType() {
		return this.type;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getSender() {
		return this.sender;
	}
}
