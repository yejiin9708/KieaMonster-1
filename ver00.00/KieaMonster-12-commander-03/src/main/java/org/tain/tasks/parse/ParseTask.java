package org.tain.tasks.parse;

import java.util.List;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.controller.WebSocketServerController;
import org.tain.db.domain.TbCmd;
import org.tain.db.service.TbCmdService;
import org.tain.tasks.recvResult.RecvResultTask;
import org.tain.tools.node.MonJsonNode;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component("ParseTask")
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
	private RecvResultTask recvResultTask;
	
	@Autowired
	private TbCmdService tbCmdService;
	
	@Autowired
	private WebSocketServerController webSocketController;
	
	public void parsing(Session session, String message) {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			MonJsonNode reqNode = null;
			MonJsonNode resNode = null;
			try {
				reqNode = new MonJsonNode(message);
				resNode = reqNode.clone();
				log.info("KANG-20210405 >>>>> {} reqNode = {}", CurrentInfo.get(), reqNode.toPrettyString());
				
				String svrCode = reqNode.getText("svrCode");
				String msgCode = reqNode.getText("msgCode");
				
				switch (msgCode) {
				case "GET_CMDS":
					// get commands
					List<TbCmd> lstCmds = this.tbCmdService.listBySvrCode(svrCode);
					MonJsonNode cmds = new MonJsonNode(MonJsonNode.getJson(lstCmds));
					// set resNode
					resNode.put("resResult", cmds);
					resNode.put("status", "SUCCESS");
					log.info("KANG-20210405 >>>>> {} resNode: {} ", CurrentInfo.get(), resNode.toPrettyString());
					// send message
					this.webSocketController.sendMessage(session, resNode.toString());
					break;
				case "CMD_RET":
					// send result to load
					this.recvResultTask.setQueue(message);
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
}
