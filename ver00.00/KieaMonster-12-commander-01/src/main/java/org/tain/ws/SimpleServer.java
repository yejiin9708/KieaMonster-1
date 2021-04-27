package org.tain.ws;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.tain.domain.TbCmd;
import org.tain.node.MonJsonNode;
import org.tain.service.worker.TbCmdService;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleServer extends WebSocketServer {

	private TbCmdService tbCmdService;
	
	public SimpleServer(InetSocketAddress inetSocketAddress, TbCmdService tbCmdService) {
		super(inetSocketAddress);
		this.tbCmdService = tbCmdService;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private Set<WebSocket> sessions = new HashSet<>();
	
	public Set<WebSocket> getSessions() {
		return this.sessions;
	}
	
	private int addSession(WebSocket session) {
		this.sessions.add(session);
		return this.sessions.size();
	}
	
	private int delSession(WebSocket session) {
		this.sessions.remove(session);
		return this.sessions.size();
	}
	
	public void sendMessage(WebSocket session, String message) {
		session.send(message);
		if (Flag.flag) log.info("[SVR -> CLI] " + message);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Override
	public void onStart() {
		if (Flag.flag) log.info("[onStart] server started successfully!");
	}
	
	@Override
	public void onOpen(WebSocket session, ClientHandshake handshake) {
		int size = this.addSession(session);
		
		String msg = "(SZ:" + size + ") new connection to " + session.getRemoteSocketAddress() + " " + handshake.getResourceDescriptor();
		if (Flag.flag) log.info("[onOpen] {}", msg);
	}

	@Override
	public void onClose(WebSocket session, int code, String reason, boolean remote) {
		int size = this.delSession(session);
		
		String msg = "(SZ:" + size + ") closed. " + session.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason;
		if (Flag.flag) log.info("[onClose] {}", msg);
	}

	@Override
	public void onMessage(WebSocket session, String message) {
		if (Flag.flag) log.info("[onMessage] received message of String...");
		
		try {
			MonJsonNode node = null;
			String svrName = null;
			String reqCode = null;
			
			//////////////////////////////////////////////
			if (Flag.flag) {
				// recv
				if (Flag.flag) log.info("[SVR <- CLI] " + message);
				node = new MonJsonNode(message);
				if (Flag.flag) log.info("REQ: {} ", node.toPrettyString());
				svrName = node.getText("svrName");
				reqCode = node.getText("reqCode");
			}
			
			if (Flag.flag) log.info(">>>>> svrName: {}, reqCode: {}", svrName, reqCode);
			
			switch (reqCode) {
			case "GETCMDS":
				//////////////////////////////////////////////
				if (Flag.flag) {
					// send test 2
					List<TbCmd> list = this.tbCmdService.listBySvrCode(svrName);
					System.out.println("list.size() = " + list.size());
					
					MonJsonNode cmds = new MonJsonNode(MonJsonNode.getJson(list));
					node.put("resResult", cmds);
					if (Flag.flag) log.info("RES: {} ", node.toPrettyString());
					this.sendMessage(session, node.toString());
				}
				break;
			default:
				//////////////////////////////////////////////
				if (Flag.flag) {
					// send test 1
					MonJsonNode cmds = new MonJsonNode("[]");
					cmds.add("CMD01");
					cmds.add("CMD02");
					cmds.add("CMD03");
					node.put("resResult", cmds);
					if (Flag.flag) log.info("RES: {} ", node.toPrettyString());
					this.sendMessage(session, node.toString());
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessage(WebSocket session, ByteBuffer bytes) {
		// TODO: KANG-20210427
	}

	@Override
	public void onError(WebSocket session, Exception ex) {
		if (Flag.flag) log.info("[onError] an error occurred on connection " + session.getRemoteSocketAddress() + ":" + ex);
	}
}
