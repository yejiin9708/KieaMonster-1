package org.tain.tasks.parse;

import java.util.List;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.controller.WebSocketController;
import org.tain.domain.TbCmd;
import org.tain.node.MonJsonNode;
import org.tain.service.worker.TbCmdService;
import org.tain.tasks.recvresult.RecvResultTask;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component("ParseTask")
@Slf4j
public class ParseTask {

	@Autowired
	private RecvResultTask recvResultTask;
	
	@Autowired
	private TbCmdService tbCmdService;
	
	@Autowired
	private WebSocketController webSocketController;
	
	@Bean
	public void startParseTask() throws Exception {
		System.out.println("KANG-20210405 >>>>> Hello, Starting of ParseTask.");
		
		if (Flag.flag) {
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void parsing(Session session, String reqMessage) {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			MonJsonNode reqNode = null;
			MonJsonNode resNode = null;
			try {
				resNode = new MonJsonNode("{}");
				reqNode = new MonJsonNode(reqMessage);
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
					log.info("KANG-20210405 >>>>> {} resNode: {} ", CurrentInfo.get(), resNode.toPrettyString());
					// send message
					this.webSocketController.sendMessage(session, resNode.toString());
					break;
				case "CMD_RET":
					// send result to load
					this.recvResultTask.setQueueLoadResult(reqMessage);
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
