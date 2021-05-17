package org.tain.vo;

import javax.websocket.Session;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionInfo {

	private Session session;
	
	private String userId;
	
	private String groupCd;
	
	private String rollCd;
	
	private String sections;
	
	@Builder
	public SessionInfo(
			Session session,
			String userId,
			String groupCd,
			String rollCd
			) {
		this.session = session;
		this.userId = userId;
		this.groupCd = groupCd;
		this.rollCd = rollCd;
	}
}

/*

{
	"msgCode": "CMD_AUTH",
	"userId": "USERID",
	"groupCd": "GROUP",
	"rollCD": "ROLL",
	"sections": ""
}

*/
