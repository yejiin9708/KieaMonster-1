package org.tain.tasks.parse;

import java.util.List;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.tain.controller.WebSocketServerController;
import org.tain.db.domain.TbCmd;
import org.tain.tools.node.MonJsonNode;
import org.tain.tools.queue.MonQueueBox;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component("ParseTask")
@DependsOn({"MonQueueBox"})
@Slf4j
public class ParseTask {

	@Bean
	public void startParseTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of ParseTask.");
		
		if (Boolean.TRUE) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private MonQueueBox monQueueBox;
	
	@Autowired
	private WebSocketServerController webSocketController;
	
	public synchronized void parsing(Session session, String message) {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			MonJsonNode node = null;
			try {
				node = new MonJsonNode(message);
				log.info("KANG-20210405 >>>>> node {} = {}", CurrentInfo.get(), node.toPrettyString());
				
				String msgCode = node.getText("msgCode");
				switch (msgCode) {
				case "GET_CMDS":
					// get commands: worker -> commander -> worker
					msg_GET_CMDS(session, node);
					break;
				case "CMD_RET":
					// send result to load: worker -> commander -> monitor
					this.monQueueBox.setQueueRecvResult(node);
					break;
				case "AUTH_BRW":
					// auth browser: monitor -> commander -> monitor
					msg_AUTH_BRW(session, node);
					break;
				default:
					throw new Exception("ERROR: couldn't parse the msgCode [" + msgCode + "]");
					//break;
				}
			} catch (Exception e) {
				//e.printStackTrace();
				log.error("KANG-20210405 >>>>> error message: {} at {}", e.getMessage(), CurrentInfo.get());
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void msg_GET_CMDS(Session session, MonJsonNode node) throws Exception {
		if (Boolean.TRUE) {
			MonJsonNode reqNode = node;
			MonJsonNode resNode = reqNode.clone();
			
			String svrCode = reqNode.getText("svrCode");
			List<TbCmd> lstCmds = null; // this.tbCmdService.listBySvrCode(svrCode);
			MonJsonNode cmds = new MonJsonNode(MonJsonNode.getJson(lstCmds));
			
			// set resNode
			resNode.put("resResult", cmds);
			resNode.put("status", "SUCCESS");
			log.info("KANG-20210405 >>>>> {} {} resNode: {} ", svrCode, CurrentInfo.get(), resNode.toPrettyString());
			
			// send message
			this.webSocketController.sendMessage(session, resNode.toString());
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void msg_AUTH_BRW(Session session, MonJsonNode node) throws Exception {
		if (Boolean.TRUE) {
			MonJsonNode reqNode = node;
			MonJsonNode resNode = reqNode.clone();
			
			String svrCode = reqNode.getText("svrCode");
			List<TbCmd> lstCmds = null; // this.tbCmdService.listBySvrCode(svrCode);
			MonJsonNode cmds = new MonJsonNode(MonJsonNode.getJson(lstCmds));
			
			// set resNode
			resNode.put("resResult", cmds);
			resNode.put("status", "SUCCESS");
			log.info("KANG-20210405 >>>>> {} {} resNode: {} ", svrCode, CurrentInfo.get(), resNode.toPrettyString());
			
			// send message
			this.monQueueBox.setQueueLinkMonitor(resNode);
		}
	}
}
