package org.tain.ws;

import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.tain.commands.RecvCommands;
import org.tain.node.MonJsonNode;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleClient extends WebSocketClient {

	private RecvCommands recvCommands;
	
	public SimpleClient(URI serverUri, Draft draft) {
		super(serverUri, draft);
	}
	
	public SimpleClient(URI serverUri, RecvCommands recvCommands) {
		super(serverUri);
		this.recvCommands = recvCommands;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public void sendMessage(String message) {
		this.send(message);
		if (Flag.flag) log.info("[SVR <- CLI] " + message);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Override
	public void onOpen(ServerHandshake handshakedata) {
		if (Flag.flag) log.info("[onOpen] new connection opened");
		
		if (Flag.flag) {
			try {
				MonJsonNode send = new MonJsonNode("{}");
				send.put("svrCode", "TEST01");
				send.put("msgCode", "GET_CMDS");
				if (Flag.flag) log.info("REQ: {}", send.toPrettyString());
				
				this.sendMessage(send.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		if (Flag.flag) log.info("[onClose] closed with exit_code: " + code + ", additional info: " + reason);
		System.exit(0);
	}
	
	@Override
	public void onMessage(String message) {
		if (Flag.flag) log.info("[onMessage] received message of String...");
		
		if (!Flag.flag) {
			if (Flag.flag) log.info("[SVR -> CLI] " + message);
		}
		
		MonJsonNode recv = null;
		if (Flag.flag) {
			if (Flag.flag) log.info("[SVR -> CLI] " + message);
			
			try {
				recv = new MonJsonNode(message);
				if (Flag.flag) log.info("RES: {}", recv.toPrettyString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String msgCode = null;
		if (Flag.flag) {
			msgCode = recv.getText("msgCode");
			if (Flag.flag) log.info(">>>>> msgCode: {}", msgCode);
			
			switch (msgCode) {
			case "GET_CMDS":
				this.recvCommands.convertToMap(recv);
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public void onMessage(ByteBuffer bytes) {
		// TODO: KANG-20210427
		/*
		if (Flag.flag) log.info("[onMessage] received message of ByteBuffer...");
		
		if (Flag.flag) {
			String message = bytes.toString();
			if (Flag.flag) log.info("[SVR -> CLI] " + message);
		}
		
		if (!Flag.flag) {
			String message = bytes.toString();
			if (Flag.flag) log.info("[SVR -> CLI] " + message);
			
			try {
				MonJsonNode recv = new MonJsonNode(message);
				if (Flag.flag) log.info(">>>>> {}: {} ", CurrentInfo.get(), recv.toPrettyString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
	}

	@Override
	public void onError(Exception ex) {
		if (Flag.flag) log.info("[onError] an error occurred: " + ex);
	}
}
