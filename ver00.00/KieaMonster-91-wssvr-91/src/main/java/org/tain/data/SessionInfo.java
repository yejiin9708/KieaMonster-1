package org.tain.data;

import javax.websocket.Session;

import lombok.Data;

@Data
public class SessionInfo {

	private Session session;
	
	private String role;
	
	private String userId;
	
	private String comment;
	
	private String browserInfo;
}
